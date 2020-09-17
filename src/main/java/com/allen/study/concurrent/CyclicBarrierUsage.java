package com.allen.study.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ThreadPoolExecutor;

import com.allen.tool.thread.ThreadPoolExecutorUtil;

public class CyclicBarrierUsage {

	/**
	 * 所有线程都执行完再继续执行
	 */
	public static void barrierAll() {
		// CyclicBarrier cyclicBarrier = new CyclicBarrier(6);
		CyclicBarrier cyclicBarrier = new CyclicBarrier(5, new Runnable() {
			@Override
			public void run() {
				System.out.println("Current Thread Name : " + Thread.currentThread().getName());
			}
		});
		ThreadPoolExecutor executor = ThreadPoolExecutorUtil.getExecutor("CyclicBarrierUsage");
		for (int i = 0; i < 5; i++) {
			executor.execute(new BarrierTask(cyclicBarrier));
		}
		// 2-测试循环使用CyclicBarrier
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("CyclicBarrier重用");
		for (int i = 0; i < 5; i++) {
			executor.execute(new BarrierTask(cyclicBarrier));
		}

		// 1-验证等待时间
//		try {
//			Thread.sleep(5000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		executor.execute(new BarrierTask(cyclicBarrier));
	}

	public static void main(String[] args) {
		barrierAll();
	}

	public static class BarrierTask implements Runnable {
		private CyclicBarrier cyclicBarrier;

		public BarrierTask(CyclicBarrier cyclicBarrier) {
			this.cyclicBarrier = cyclicBarrier;
		}

		@Override
		public void run() {
			System.out.println(Thread.currentThread().getName() + " task is beginning...");
			try {
				Thread.sleep(5000);
				System.out.println(Thread.currentThread().getName() + " task is done, waiting other task!");
				cyclicBarrier.await();
				// 1-验证等待时间
//				try {
//					cyclicBarrier.await(2000, TimeUnit.MILLISECONDS);
//				} catch (TimeoutException e) {
//					e.printStackTrace();
//				}
			} catch (InterruptedException | BrokenBarrierException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + " all task is Done");
		}
	}
}
