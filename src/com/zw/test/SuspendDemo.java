package com.zw.test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 模拟 suspend
 */
public class SuspendDemo extends Thread {
	public volatile boolean suspendRequested = false;
	private Lock suspendLock = new ReentrantLock();
	private Condition suspendCondition = suspendLock.newCondition();

	@Override
	public void run() {
		while (true) {
			if (suspendRequested) {
				suspendLock.lock();
				try {
					while (suspendRequested)
						suspendCondition.await();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					suspendLock.unlock();
				}
			}

			// TODO
			System.out.println(Thread.currentThread().getName() + ": " + System.currentTimeMillis());
		}
	}

	public void requestSuspend() {
		suspendRequested = true;
	}

	public void requestResume() {
		suspendRequested = false;
		suspendLock.lock();
		try {
			suspendCondition.signalAll();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			suspendLock.unlock();
		}
	}
}
