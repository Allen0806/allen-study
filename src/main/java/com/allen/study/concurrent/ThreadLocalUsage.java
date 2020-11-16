package com.allen.study.concurrent;

public class ThreadLocalUsage {

	private static ThreadLocal<String> tokenThreadLocal = new ThreadLocal<>();

	public ThreadLocalUsage() {
		// TODO Auto-generated constructor stub
	}

	public void processUser(String token) {
		try {
			tokenThreadLocal.set(token);
			processStep1();
			processStep2();
		} finally {
			tokenThreadLocal.remove();
		}
	}

	public void processStep1() {
		String token = tokenThreadLocal.get();
		System.out.println("processStep1:" + token);
		tokenThreadLocal.set(token + "-step1");
	}

	public void processStep2() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String token = tokenThreadLocal.get();
		System.out.println("processStep2:" + token);
	}

	public void processUser2(String token) {
		try (var ctx = new UserContext(token)) {
			processStep3();
		}
	}

	public void processStep3() {
		String token = UserContext.currentUser();
		System.out.println(token);
	}

	public static class UserContext implements AutoCloseable {
		static final ThreadLocal<String> ctx = new ThreadLocal<>();

		public static String currentUser() {
			return ctx.get();
		}

		public UserContext(String user) {
			ctx.set(user);
		}

		@Override
		public void close() {
			ctx.remove();
		}
	}

	public static void main(String[] args) {
		ThreadLocalUsage usage = new ThreadLocalUsage();
		Thread t = new Thread(new Runnable() {
			public void run() {
				usage.processUser2("user-11111");
			}
		});
		t.start();
		usage.processUser2("user-00001");
	}

}
