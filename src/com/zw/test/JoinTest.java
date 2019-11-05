package com.zw.test;

public class JoinTest {
	public static void main(String[] args) {
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				// try {
				// Thread.sleep(2000);
				// } catch (InterruptedException e) {
				// e.printStackTrace();
				// }

				try {
					for (int i = 0; i < 10000; i++) {
						System.out.println("i: " + i);
					}
				} finally {
					System.out.println("**** finally ****");
				}
			}
		});

		thread.start();

		// try {
		// thread.join();
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }

		// thread.yield();
		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		thread.stop();

		System.out.println("*** end ***");
	}
}
