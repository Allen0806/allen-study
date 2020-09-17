package com.allen.study.concurrent;

import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;

import com.allen.tool.thread.ThreadPoolExecutorUtil;

public class SemaphoreUsage {

	public static void use() {
		Semaphore semaphore = new Semaphore(5);
		ThreadPoolExecutor executor = ThreadPoolExecutorUtil.getExecutor("SemaphoreUsage");
		for (int i = 0; i < 10; i++) {
			executor.execute(new SemaphoreTask(i, semaphore));
		}
	}

	public static void main(String[] args) {
		use();

	}

	public static class SemaphoreTask implements Runnable {
		private int num;
		private Semaphore semaphore;

		public SemaphoreTask(int num, Semaphore semaphore) {
			this.num = num;
			this.semaphore = semaphore;
		}

		@Override
		public void run() {
			try {
				semaphore.acquire();
//				semaphore.acquire(2);
				System.out.println(Thread.currentThread().getName() + " : " + num + " task is beginning");
				Thread.sleep(2000);
				System.out.println(Thread.currentThread().getName() + " : " + num + " task is done");
				semaphore.release();
//				semaphore.release(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
