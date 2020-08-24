package com.allen.study.regulation;

@SuppressWarnings("unused")
//反例
public class UserInfo {
	private String name;

	// 非setter方法的参数名称，不能与类
	// 属性名称相同
	public void process(String name) {
		boolean condition = true;
		if (condition) {
			int age = 0;
			// ...
		}
		while (condition) {
			// 同一方法体内，不允许定义与其他
			// 代码块相同的变量名称，避免混淆
			int age = 1;
			// ..
		}
	}

	public void deal() {
		// 方法体内不允许定义与类属性相同
		// 名称的变量
		String name = null;
		// ..
	}

	public static void main(String[] args) {
		Integer i = 12345;
		Integer j = 12345;

		// 下面的执行结果为输出true
		if (i.equals(j)) {
			System.out.println("true");
		} else {
			System.out.println("false");
		}

		// 下面的执行结果为输出false
		if (i == j) {
			System.out.println("true");
		} else {
			System.out.println("false");
		}
	}

}

@SuppressWarnings("unused")
class SystemUser extends UserInfo {
	// 子类内不允许定义与父类名称相同的属性
	private String name;
}