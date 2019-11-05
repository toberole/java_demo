package com.zw.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Test06 {
	public static void main(String[] args) {
		// test01();
		
		test02();
	}

	private static void test02() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("a", "a");
		map.put("b", "b");
		map.put("c", "c");
		
		Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<String, String> entry = iterator.next();
			System.out.println(entry.getKey());
			System.out.println(entry.getValue());
			
			if (entry.getKey().equals("a")) {
				iterator.remove();
			}
		}
		
		iterator = map.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<String, String> entry = iterator.next();
			System.out.println(entry.getKey());
			System.out.println(entry.getValue());
			
			if (entry.getKey().equals("a")) {
				iterator.remove();
			}
		}
	}

	private static void test01() {
		List<String> list = new ArrayList<String>();
		list.add("abc");
		list.add("bbc");
		list.add("cbc");
		Iterator<String> it = list.iterator();
		while (it.hasNext()) {
			String str = it.next();
			System.out.println(str);
			if (str.equals("abc")) {
				it.remove();
			}
		}
		System.out.println("***************");
		for (String s : list) {
			System.out.println(s);
		}
	}
}
