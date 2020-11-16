package com.allen.study.algorithm;

/**
 * 斐波那契算法
 * 
 * @author Allen
 * @date 2020年5月13日
 * @since 1.0.0
 *
 */
public class Fibonacci {
	public int fibonacciNumber(int n) {
		// 考查对参数合法性的校验
		if (n < 1) {
			throw new IllegalArgumentException("参数不合法");
		}
		// 考察是否考虑了初始情况
		if (n == 1 || n == 2) {
			return 2;
		}
		int a1 = 2;
		int a2 = 2;
		int temp;
		for (int i = 3; i <= n; i++) {
			temp = a2;
			a2 = a2 + a1;
			a1 = temp;
		}
		return a2;
	}

	public static void main(String[] args) {
		Fibonacci f = new Fibonacci();
		int n = 4;
		System.out.println(f.fibonacciNumber(n));
	}

}
