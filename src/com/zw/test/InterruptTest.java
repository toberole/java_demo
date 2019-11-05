package com.zw.test;

public class InterruptTest {
	public static void main(String[] args) {
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				// try {
				// Thread.sleep(2000);
				// } catch (InterruptedException e) {
				// e.printStackTrace();
				// }

				for (int i = 0; i < 1000; i++) {
					System.out.println("i: " + i);
				}
			}
		});

		thread.start();

		thread.interrupt();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
		}
	}
}
