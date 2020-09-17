package com.allen.study.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;

import com.allen.tool.thread.ThreadPoolExecutorUtil;

public class CountDownLatchUsage {

	/**
	 * 所有线程都执行完再继续执行
	 */
	public static void waitingAll() {
		CountDownLatch countDownLatch = new CountDownLatch(5);
		ThreadPoolExecutor executor = ThreadPoolExecutorUtil.getExecutor("CountDownLatchUsage");
		for (int i = 0; i < 5; i++) {
			executor.execute(new LatchTask(countDownLatch));
		}
		System.out.println("Task Start!");
		try {
			countDownLatch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("All Task is Done!");
	}

	/**
	 * 同时启动多个线程
	 */
	public static void beginningAll() {
		CountDownLatch countDownLatch = new CountDownLatch(1);
		ThreadPoolExecutor executor = ThreadPoolExecutorUtil.getExecutor("countDownTest");
		for (int i = 0; i < 5; i++) {
			executor.execute(new LatchTask2(countDownLatch));
		}
		System.out.println("Task Start!");
		try {
			Thread.sleep(1000);
			countDownLatch.countDown();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}

	public static void main(String[] args) {
		beginningAll();

	}

	public static class LatchTask implements Runnable {

		private CountDownLatch countDownLatch;

		public LatchTask(CountDownLatch countDownLatch) {
			this.countDownLatch = countDownLatch;
		}

		public void run() {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				System.out.println(Thread.currentThread().getName() + "  Task is Done");
				countDownLatch.countDown();
			}

		}
	}

	public static class LatchTask2 implements Runnable {

		private CountDownLatch countDownLatch;

		public LatchTask2(CountDownLatch countDownLatch) {
			this.countDownLatch = countDownLatch;
		}

		public void run() {
			try {
				countDownLatch.await();
				System.out.println(Thread.currentThread().getName() + "  Task is Done");
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

}
