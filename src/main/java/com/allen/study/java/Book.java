package com.allen.study.java;

public class Book {
	public static void main(String[] args) {
		staticFunction();
	}

	static Book book = new Book();

	static {
		System.out.println("书的静态代码块");
	}

	{
		System.out.println("书的普通代码块");
	}

	Book() {
		System.out.println("书的构造方法");
		System.out.println("price=" + price + ",amount=" + amount);
	}

	public static void staticFunction() {
		System.out.println("书的静态方法");
	}

	int price = 110;
	static int amount = 112;
	public static final String STRING_FINAL = "字符串常量";

}
