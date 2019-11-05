package com.zw.test;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.LinkedList;
import java.util.List;

public class PhantomReferenceTest {
	private static final List<Object> TEST_DATA = new LinkedList<>();
	private static final ReferenceQueue<TestClass> QUEUE = new ReferenceQueue<>();

	public static void main1(String[] args) {
		TestClass obj = new TestClass("Test");
		PhantomReference<TestClass> phantomReference = new PhantomReference<>(obj, QUEUE);
		// 该线程不断读取这个虚引用，并不断往列表里插入数据，以促使系统早点进行GC
		new Thread(() -> {
			while (true) {
				TEST_DATA.add(new byte[1024 * 100]);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
					Thread.currentThread().interrupt();
				}
				System.out.println(phantomReference.get());
			}
		}).start();

		// 这个线程不断读取引用队列，当弱引用指向的对象呗回收时，该引用就会被加入到引用队列中
		new Thread(() -> {
			while (true) {
				Reference<? extends TestClass> poll = QUEUE.poll();
				if (poll != null) {
					System.out.println("--- 虚引用对象被jvm回收了 ---- " + poll);
					System.out.println("--- 回收对象 ---- " + poll.get());
				}
			}
		}).start();

		obj = null;

		try {
			Thread.currentThread().join();
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	static class TestClass {
		private String name;

		public TestClass(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return "TestClass - " + name;
		}

		@Override
		protected void finalize() throws Throwable {
			System.out.println("TestClass finalize");
			super.finalize();
		}
	}

	public static void main(String[] args) throws Exception {
		// 创建一个字符串对象
		String str = new String("疯狂Java讲义");
		// 创建一个引用队列
		ReferenceQueue rq = new ReferenceQueue();
		// 创建一个虚引用，让此虚引用引用到"疯狂Java讲义"字符串
		PhantomReference pr = new PhantomReference(str, rq);
		// 切断str引用和"疯狂Java讲义"字符串之间的引用
		str = null;
		// 取出虚引用所引用的对象，并不能通过虚引用获取被引用的对象，所以此处输出null
		System.out.println(pr.get()); // ①
		// 强制垃圾回收
		System.gc();
		System.runFinalization();
		// 垃圾回收之后，虚引用将被放入引用队列中
		// 取出引用队列中最先进入队列中的引用与pr进行比较
		System.out.println(rq.poll() == pr); // ②
	}
}
