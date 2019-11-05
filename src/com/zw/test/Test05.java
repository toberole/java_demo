package com.zw.test;

import java.util.concurrent.FutureTask;

public class Test05 {
	public static void main(String[] args) {
		FutureTask<String> futureTask = null;
		Thread thread  = new Thread(futureTask);
	}
}
