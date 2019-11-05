package com.zw.test;

import java.util.Collection;

public class Test04 {
	public static void main(String[] args) {
		String a = "A";
		String b = new String("A");

		System.out.println(a == b);
		System.out.println(a.equals(b));

		String s = "abc";
		StringBuffer sb = new StringBuffer(s);
		sb = sb.reverse();
		System.out.println(sb);

		StringBuilder sb1 = new StringBuilder(s);
		sb1 = sb1.reverse();
		System.out.println(sb1);

		String s1 = "123456";
		char chs[] = s1.toCharArray();
		reverse(chs);
		System.out.println(new String(chs));

		System.out.println(Math.round(1.5));
		System.out.println(Math.round(-1.5));

		Collection<String> collection;

		int arr[] = { 3, 5, 1, 2, 4 };
		sort_mp(arr);
		for (int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}
	}

	public static void reverse(char[] chs) {
		if (chs != null && chs.length >= 2) {
			for (int i = 0; i < chs.length / 2; i++) {
				char ch = chs[i];
				chs[i] = chs[chs.length - i - 1];
				chs[chs.length - i - 1] = ch;
			}
		}
	}

	public static void sort_mp(int arr[]) {
		for (int i = 0; i < arr.length - 1; i++) {
			for (int j = 0; j < arr.length - 1 - i; j++) {
				if (arr[j] > arr[j + 1]) {
					int k = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = k;
				}
			}
		}
	}
}
