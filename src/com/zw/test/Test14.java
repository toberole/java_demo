package com.zw.test;

public class Test14 {
	private static ThreadLocal<String> local = new ThreadLocal<>();
	private static ThreadLocal<String> local1 = new ThreadLocal<>();

	public static void main(String[] args) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				local.set("Thread1 huhu 111 ***");
				local1.set("Thread1 huhu 222 ***");

				System.out.println("Thread1: " + local.get());
				System.out.println("Thread1: " + local1.get());
			}
		}).start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				local.set("Thread2 huhu 111");
				local1.set("Thread2 huhu 222");

				System.out.println("Thread2: " + local.get());
				System.out.println("Thread2: " + local1.get());
			}
		}).start();

		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
