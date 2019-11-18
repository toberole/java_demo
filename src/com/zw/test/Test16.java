package com.zw.test;

public class Test16 {
	public static void main(String[] args) {
		byte[] allocation1, allocation2,allocation3,allocation4,allocation5;
		allocation1 = new byte[64000*1024];
		allocation2 = new byte[1000*1024];
		allocation3 = new byte[1000*1024];
		allocation4 = new byte[1000*1024];
		allocation5 = new byte[1000*1024];
		
		int i = 100;
		for (int j = 0; j < i; j++) {
			System.out.println("i: " + i);
		}

		System.out.println("end");
	}
}
