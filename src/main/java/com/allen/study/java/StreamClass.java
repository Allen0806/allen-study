package com.allen.study.java;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.LongStream;

public class StreamClass {

	private static Function<String, Integer> parseFunc = Integer::parseInt;

	public static long factorialStreams(long n) {
		return LongStream.rangeClosed(1, n).reduce(1, (long a, long b) -> a * b);
	}

	public static void parseInt(String s) {
		System.out.println(parseFunc.apply(s));
	}

	public static Optional<Integer> getMaxValue() {
		ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
		for (int i = 0; i < 10; i++) {
			map.put("" + i, i);
		}
		Optional<Integer> maxValue = Optional.of(map.reduceValues(1, Integer::max));
		System.out.println(maxValue.get());
		System.out.println(map.mappingCount());
		return maxValue;
	}

	public static void main(String[] args) {
//		System.out.println(factorialStreams(10));
//		parseInt("100");
//		getMaxValue();
//		int[] array = new int[]{2, 3, 1, 0, 5};
//		// [2, 3, 1, 0, 5]
//		// [2, 5, 1, 0, 5]
//		// [2, 5, 6, 0, 5]
//		// [2, 5, 6, 6, 5]
//		Arrays.parallelPrefix(array, (left, right) ->{
//		    System.out.println(Arrays.toString(array));
//		    return left + right;
//		});
//
//		// 输出 [2, 5, 6, 6, 11]
//		System.out.println(Arrays.toString(array));

		String s = String.join(", ", "1", "2", "3", "4");
		System.out.println(s);

	}

}
