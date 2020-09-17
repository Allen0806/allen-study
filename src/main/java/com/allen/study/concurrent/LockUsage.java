package com.allen.study.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class LockUsage {

	private static final ReentrantLock lock = new ReentrantLock();
	private static int count = 0;

	public LockUsage() {
		// TODO Auto-generated constructor stub
	}

	public void testLock() {
		lock.lock();
		try {
			for (int i = 0; i < 100; i++) {
				count++;
			}
			System.out.println(Thread.currentThread().getName() + ":" + count);

		} finally {
			lock.unlock();
		}
	}

	public void testTryLock() {
		try {
			if (lock.tryLock(2, TimeUnit.SECONDS)) {
				try {
					for (int i = 0; i < 100; i++) {
						count++;
					}
					System.out.println(Thread.currentThread().getName() + ":" + count);
//					Thread.sleep(3000);
				} finally {
					lock.unlock();
				}
			} else {
				System.out.println(Thread.currentThread().getName() + ": 未获取到锁");
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		LockUsage lockUsage = new LockUsage();
		Thread t1 = new Thread(() -> lockUsage.testTryLock(), "Thread1");
		Thread t2 = new Thread(() -> lockUsage.testTryLock(), "Thread2");
		Thread t3 = new Thread(() -> lockUsage.testTryLock(), "Thread3");

		t1.start();
		t2.start();
		t3.start();

	}

}
