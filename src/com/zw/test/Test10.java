package com.zw.test;

public class Test10 {
	public Test10() {
		System.out.println("*** Test10 ***");
	}
	
	public native int test4();
	
	public int test3() {
		int i = 0;
		return i;
	}
	public void test() {
		synchronized (this) {
			System.out.println("hello test");
		}
	}

	public synchronized void test2() {
		System.out.println("hello test2");
	}

	public static void main(String[] args) {
		System.out.println("hello main");
	}
}
