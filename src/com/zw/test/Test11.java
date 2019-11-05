package com.zw.test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Test11 {
	private static ReentrantLock lock = new ReentrantLock();
	private static Condition condition = lock.newCondition();
	private static volatile int tag = 0;
	
	private static int count = 50;

	public static void main(String[] args) {
		new Thread(new P()).start();
		new Thread(new C()).start();
	}

	private static class P implements Runnable {
		@Override
		public void run() {
			for (int i = 0; i < count; i++) {
				try {
					lock.lock();
					while (tag >= 1) {
						try {
							condition.await();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}

					tag = 1;
					System.out.println("P 生产 " + tag);
					condition.signalAll();
				} finally {
					lock.unlock();
				}
			}
		}
	}

	private static class C implements Runnable {
		@Override
		public void run() {
			for (int i = 0; i < count; i++) {
				try {
					lock.lock();
					while (tag < 1) {
						try {
							condition.await();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}

					tag = 0;
					System.out.println("C 消费 " + tag);
					condition.signalAll();
				} finally {
					lock.unlock();
				}
			}
		}
	}
}
