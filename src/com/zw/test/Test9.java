package com.zw.test;

public class Test9 {
	public void test1() {
		synchronized (this) {
			System.out.println("---- 1");
		}
	}

	public synchronized void test2() {
		System.out.println("---- 2");
	}

	public static void main(String[] args) {
		System.out.println("main thread");
	}
}
