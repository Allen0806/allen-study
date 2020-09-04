package com.allen.test;

import java.util.UUID;

public class Student extends People {

	public Student() {
		super.test();
	}

	@Override
	public void test() {
		System.out.println("This is son");
	}

	public static void main(String[] args) throws Exception{
		UUID uuid = java.util.UUID.randomUUID();
		System.out.println(uuid.toString());
		System.out.println(uuid.toString().replace("-", ""));
		System.out.println(uuid.toString().length());
	}
}
