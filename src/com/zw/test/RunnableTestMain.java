package com.zw.test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class RunnableTestMain {

	public static void main(String[] args) {
		ExecutorService pool = Executors.newFixedThreadPool(10);

		/**
		 * execute(Runnable x) 没有返回值。 可以执行任务，但无法判断任务是否成功完成。
		 */
		pool.execute(new RunnableTest("Task1"));

		/**
		 * submit(Runnable x) 返回一个future。 可以用这个future来判断任务是否成功完成。
		 */
		Future future = pool.submit(new RunnableTest("Task2"));
		
		Future f1 =  pool.submit(new Callable<String>() {

			@Override
			public String call() throws Exception {
				String resString = "submit Callable";
				return resString;
			}
		});
		
		try {
			System.out.println(f1.get());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		try {
			System.out.println("------ future.get() -----");
			if (future.get() == null) {// 如果Future's get返回null，任务完成
				System.out.println("任务完成");
			}
		} catch (Exception e) {
			System.out.println("***** Exception: " + e.getCause().getMessage());
		}
	}

	static class RunnableTest implements Runnable {
		private String taskName;

		public RunnableTest(final String taskName) {
			this.taskName = taskName;
		}

		@Override
		public void run() {
			System.out.println("Inside " + taskName);

			throw new RuntimeException("RuntimeException from inside " + taskName);
		}
	}
}
