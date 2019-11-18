package com.zw.test;

public class ReferenceCountingGc {
	private Object instance = null;

	public static void main(String[] args) {
		ReferenceCountingGc objA = new ReferenceCountingGc();
		ReferenceCountingGc objB = new ReferenceCountingGc();
		objA.instance = objB;
		objB.instance = objA;
		objA = null;
		objB = null;

		System.out.println("end");

		

	}
}
