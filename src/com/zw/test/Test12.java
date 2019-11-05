package com.zw.test;

public class Test12 {
	private static Object lock = new Object();
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
				synchronized (lock) {
					while (tag >= 1) {
						try {
							lock.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}

					tag = 1;
					System.out.println("P 生产 " + tag + "  " + System.currentTimeMillis());
					lock.notifyAll();
				}
			}
		}
	}

	private static class C implements Runnable {
		@Override
		public void run() {
			for (int i = 0; i < count; i++) {
				synchronized (lock) {
					while (tag < 1) {
						try {
							lock.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}

					tag = 0;
					System.out.println("C 消费 " + tag + "  " + System.currentTimeMillis());
					lock.notifyAll();
				}
			}
		}
	}
}
