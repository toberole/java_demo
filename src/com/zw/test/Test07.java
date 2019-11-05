package com.zw.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Test07 {
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	public static void main(String[] args) {
		Map<String, String> map = new HashMap<>();
		System.out.println(f(2));

		float a = 1.0f - 0.9f;
		float b = 0.9f - 0.8f;
		System.out.println(a);// 0.100000024
		System.out.println(b);// 0.099999964
		System.out.println(a == b);// false

		String[] arrs = new String[] { "hello", "world" };
		List<String> list1 = Arrays.asList(arrs);
		// java.lang.UnsupportedOperationException
		// list1.add("ssss");

		ConcurrentHashMap<String, String> map2 = new ConcurrentHashMap<>();
		map2.put("huhu", "huhu");
		map2.put("abab", "abab");

		for (Map.Entry<String, String> en : map2.entrySet()) {
			map2.remove(en.getKey());
		}

		System.out.println("------------- " + map2.size());

		List<String> list = new ArrayList<String>();
		list.add("1");
		list.add("2");
		for (String s : list) {// foreach 底层是采用的迭代器
			// if ("2".equals(s)) {
			// list.remove(s);
			// }
			// System.out.println("s: "+s);
		}

		int[] ins = new int[] { 1, 2, 3 };
		ins = Arrays.copyOf(ins, 5);
		for (int i : ins) {
			System.out.println("i: " + i);
		}

		ins = new int[] { 1, 2, 3, 4, 5, 6, 7 };
		int index = b_s(ins, 4);
		System.out.println("***** index: " + index);

		ins = new int[] { 1, 5, 3, 4, 6, 7, 2 };
		m_b(ins);
		for (int i = 0; i < ins.length; i++) {
			System.out.println(ins[i]);
		}

	}

	private static <T extends Number & Comparable<? super T>> T min(T[] values) {
		if (values == null || values.length == 0)
			return null;
		T min = values[0];
		for (int i = 1; i < values.length; i++) {
			if (min.compareTo(values[i]) > 0)
				min = values[i];
		}
		return min;
	}

	public int test() {
		return 0;
	}

	public static int f(int value) {
		try {
			return value * value;
		} finally {
			if (value == 2) {
				return 0;
			}
		}
	}

	public static class Singleton {
		private static Singleton instance;

		private Singleton() {
		}

		public static Singleton getInstance() {
			if (instance == null) {
				synchronized (Singleton.class) {
					if (instance == null) {
						instance = new Singleton();
					}
				}
			}

			return instance;
		}
	}

	/**
	 * 冒泡排序
	 */
	private static void m_b(int arr[]) {
		for (int i = 0; i < arr.length - 1; i++) {
			for (int j = 0; j < arr.length - 1 - i; j++) {
				if (arr[j] > arr[j + 1]) {
					int temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
			}
		}
	}

	/**
	 * 二分查找
	 */
	private static int b_s(int arr[], int n) {
		int index = -1;

		int start = 0;
		int end = arr.length - 1;

		while (start <= end) {
			int k = (start + end) / 2;
			int temp = arr[k];
			if (temp == n) {
				index = k;
				System.out.println("index: " + k);
				break;
			} else if (temp > n) {
				end = temp - 1;
			} else {
				start = temp + 1;
			}
		}

		return index;
	}
}
