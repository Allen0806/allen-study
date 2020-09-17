package com.allen.study.concurrent;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.allen.tool.thread.CustomizableThreadFactory;

public class ScheduledExecutorServiceUsage {

	public static void executeFixedRate() {

		Runnable r = new Runnable() {
			public void run() {
				String threadName = Thread.currentThread().getName();
				System.out.println(threadName + " start: " + System.currentTimeMillis());
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(threadName + " end: " + System.currentTimeMillis());
			}
		};

		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1,
				new CustomizableThreadFactory("Schedule"));
		for (int i = 0; i < 10; i++) {
			// 后面5个任务不会被执行
			executor.scheduleAtFixedRate(r, 0, 5, TimeUnit.SECONDS);
		}

	}

	public static void executeFixedDelay() {
		Runnable r = new Runnable() {
			public void run() {
				String threadName = Thread.currentThread().getName();
				System.out.println(threadName + " start: " + System.currentTimeMillis());
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(threadName + " end: " + System.currentTimeMillis());
			}
		};
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1,
				new CustomizableThreadFactory("Schedule"));
		executor.scheduleWithFixedDelay(r, 2, 1, TimeUnit.SECONDS);

	}

	public static void main(String[] args) {
		System.out.println("Start Time: " + System.currentTimeMillis());
		// executeFixedRate();
		executeFixedDelay();

	}

}
