package com.allen.test;

import reactor.core.publisher.Flux;

//import reactor.core.publisher.Flux;

public class ReactorTest {

	public static void main(String[] args) {
//		Flux.just("Hello", "World").subscribe(System.out::println);
//		Flux.fromArray(new Integer[] { 1, 2, 3 }).subscribe(System.out::println);
//		Flux.empty().subscribe(System.out::println);
//		Flux.range(1, 10).subscribe(System.out::println);
//		Flux.interval(Duration.of(10, ChronoUnit.SECONDS)).subscribe(System.out::println);
//
//		Flux.generate(sink -> {
//			sink.next("Hello");
//			sink.complete();
//		}).subscribe(System.out::println);
//
//		final Random random = new Random();
//		Flux.generate(ArrayList::new, (list, sink) -> {
//			int value = random.nextInt(100);
//			list.add(value);
//			sink.next(value);
//			if (list.size() == 10) {
//				sink.complete();
//			}
//			return list;
//		}).subscribe(System.out::println);
//
//		Flux.create(sink -> {
//			for (int i = 0; i < 10; i++) {
//				sink.next(i);
//			}
//			sink.complete();
//		}).subscribe(System.out::println);

//		Mono.fromSupplier(() -> "Hello").subscribe(System.out::println);
//		Mono.justOrEmpty(Optional.of("Hello")).subscribe(System.out::println);
//		Mono.create(sink -> sink.success("Hello")).subscribe(System.out::println);

//		Flux.range(1, 100).buffer(20).subscribe(System.out::println);
//		Flux.intervalMillis(100).bufferMillis(1001).take(2).toStream().forEach(System.out::println);
//		Flux.range(1, 10).bufferUntil(i -> i % 2 == 0).subscribe(System.out::println);
//		Flux.range(1, 10).bufferWhile(i -> i % 2 == 0).subscribe(System.out::println);
//		Flux.range(1, 10).filter(i -> i % 2 == 0).subscribe(System.out::println);
//		Flux.range(1, 100).window(20).subscribe(System.out::println);

		Flux.just("a", "b").zipWith(Flux.just("c", "d")).subscribe(System.out::println);
		Flux.just("a", "b").zipWith(Flux.just("c", "d"), (s1, s2) -> String.format("%s-%s", s1, s2))
				.subscribe(System.out::println);
	}
}
