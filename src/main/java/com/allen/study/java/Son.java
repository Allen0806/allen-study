package com.allen.study.java;

public class Son extends Father {
	static {
		System.out.println("我是儿子");
	}
	
	public static void main(String[] args) {
		System.out.println("爸爸年龄：" + Son.age);

	}

}
