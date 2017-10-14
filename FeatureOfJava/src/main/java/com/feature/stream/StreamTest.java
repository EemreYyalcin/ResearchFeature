package com.feature.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import com.feature.log.Log;

public class StreamTest {

	public void baseStreamElements() {

		IntStream intStreamOf = IntStream.of(5, 6, 7, 3, 1, 453, 111);
		intStreamOf.sorted().forEach(Log::log);

		IntStream intStreamRange = IntStream.range(5, 15);
		Log.log("StreamRange", intStreamRange.count());
		intStreamRange = IntStream.rangeClosed(5, 15);
		Log.log("StreamRangeClosed", intStreamRange.count());

		DoubleStream doubleStreamOf = DoubleStream.of(1.1, 2.3, 4.2);
		Log.log("doubleStreamOf", doubleStreamOf.average());

		LongStream longStreamOf = LongStream.of(1, 5, Long.MAX_VALUE, 5);
		Log.log("longStreamOf", longStreamOf.average());
		LongStream longStreamRange = LongStream.range(456, 555);
		Log.log("longStreamRange", longStreamRange.average());

	}

	public void streamApiExamples() {

		List<String> names = Arrays.asList("Emre", "YLC", "YLCXLOG", "LOWPROHIGHTYZ", "YLC");
		Stream<String> streamList = names.stream();
		streamList.forEach(Log::log);

		streamList = names.stream();

		Stream<String> filtered = streamList.filter(e -> (e.length() > 4));
		filtered.forEach(Log::log);

		streamList = names.stream();
		streamList.distinct().forEach(Log::log);

		streamList = names.stream();
		streamList.limit(3).forEach(Log::log);

		String total = names.stream().reduce("", (x, y) -> {
			return x + y;
		});
		Log.log("Totat List:", total);
		IntStream.range(5, 15).map(n -> n * n).forEach(System.out::println);

		int result = IntStream.of(1, 2, 3, 5, 8).reduce(0, (x, y) -> {
			Log.log("x", x, "y", y);
			return x + y;
		});
		Log.log("result", result);

	}

	public void streamConvertors() {
		List<String> names = Arrays.asList("Emre", "YLC", "YLCXLOG", "LOWPROHIGHTYZ", "YLC");

		List<String> list = names.stream().collect(Collectors.toList());
		list.forEach(Log::log);

		Set<String> set = names.stream().collect(Collectors.toSet());
		set.forEach(Log::log);
		String collect = names.stream().collect(Collectors.joining(":"));
		Log.log("Joining: ", collect);
		Map<Integer, List<String>> integerListMap = names.stream()
				.collect(Collectors.groupingBy(name -> name.length()));
		integerListMap.forEach((name, value) -> {
			Log.log(name, value);
		});

	}

	public static void main(String[] args) {
		StreamTest streamTest = new StreamTest();
		streamTest.streamApiExamples();
		// streamTest.baseStreamElements();
		// streamTest.streamConvertors();

	}

}
