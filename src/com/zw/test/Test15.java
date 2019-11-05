package com.zw.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Test15 {
	public static void main(String[] args) {
		List<String> lStrings = new ArrayList<String>();
		lStrings.add("hello");
		lStrings.add("world");

		Iterator<String> iterator = lStrings.iterator();
		while (iterator.hasNext()) {
			iterator.remove();
		}

		System.out.println("end");
	}
}
