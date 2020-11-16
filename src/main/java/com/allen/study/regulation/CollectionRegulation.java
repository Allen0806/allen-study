package com.allen.study.regulation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CollectionRegulation {

	public static void testSublist() {
		List<String> parentList = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			parentList.add("str" + i);
		}
		List<String> subList = parentList.subList(1, 5);
		parentList.add("str10");
		// 以下会抛出ConcurrentModificationException异常
		subList.add("str11");
	}

	public static void testArrayAsList() {
		String[] str = new String[] { "chen", "yang", "hao" };
		List<String> list = Arrays.asList(str);
		// 抛出UnsupportedOperationException
		// list.add("yangguanbao");
		// 会改变list的值
		str[0] = "yangguanbao";
		System.out.println(list);
	}

	public static void testGenericity() {
		// 表示numberList的泛型参数是Number或其子类型，
		// 适合于从其中读取Number类型数据
		List<? extends Number> numberList = Arrays.asList(1, 2, 3, 4);
		Number item = numberList.get(0);
		System.out.println(item);
		
		// 报错，编译不通过，不能向numberList写入任何Number或其自类型的数据
		// List<? extends Number> numberList = new ArrayList<Integer>();
		// Number number1 = 100;
		// numberList.add(number1);
		

		// 表示integerList的泛型参数是Integer或其父类型，
		// 适合于向其中写入Integer类型的数据
		List<? super Integer> integerList = new ArrayList<Number>();
		Integer intItem = 100;
		// 可以写入Integer及其子类型的数据
		integerList.add(intItem);
		// 报错，编译不通过，不能从integerList读Integer类型的数据
		// Integer integer1 = integerList.get(0);
	}

	public static void testForeach() {
		List<String> list = new ArrayList<>();
		list.add("1");
		list.add("2");
		list.add("3");
		// 正例
		Iterator<String> iterator = list.iterator();
		while (iterator.hasNext()) {
			String item = iterator.next();
			if ("1".equals(item)) {
				iterator.remove();
			}
		}
		// 反例
		List<String> list2 = new ArrayList<>();
		list2.add("1");
		list2.add("2");
		list2.add("3");
		for (String item2 : list2) {
			if ("1".equals(item2)) {
				// 抛出ConcurrentModificationException异常
				// 此处的remove方法于iterator.remove方法实现不一样
				list2.remove(item2);
			}
		}
	}

	public static void testMap() {
		// 初始值设置为16
		Map<String, String> map = new HashMap<>(16);
		map.put("a", "a");
		map.put("b", "b");
		map.put("c", "c");
		// 反例
		Set<String> keySet = map.keySet();
		for (String key : keySet) {
			String value = map.get(key);
			System.out.println(value);
			// 其他操作
		}

		// 正例
		Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<String, String> entry = iterator.next();
			String key = entry.getKey();
			String value = entry.getValue();
			// 其他操作
			System.out.println(key + ":" + value);
		}
		// JDK8以后适用
		map.forEach((key, value) -> {
			// 其他操作
			System.out.println(key + ":" + value);
		});
	}

	public static void main(String[] args) {
		testMap();
	}
}
