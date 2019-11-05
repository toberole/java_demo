package com.zw.test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Test03 {
	static ExecutorService executorService = Executors.newFixedThreadPool(1);

	public static void main(String[] args) {
		Future<Object> future = executorService.submit(new Callable<Object>() {
			@Override
			public Object call() throws Exception {
				Object res = new Object();
				System.out.println(System.currentTimeMillis() + "  " + res);
				Thread.sleep(2000);
				return res;
			}
		});

		try {
			Object res = future.get();
			System.out.println(System.currentTimeMillis() + "  " + res);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
