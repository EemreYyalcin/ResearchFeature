package com.feature.function;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

import com.feature.log.Log;

public class ConsumerTest {

	public void consumerWithLambda() {
		Consumer<String> consumer = (String message) -> {
			Log.log(message);
		};
		consumer.accept("Hey There");

		consumer = e -> {
			Log.log(e);
		};
		consumer.accept("Hey There2");

		List<String> names = Arrays.asList("Emre", "Yalcin");
		names.forEach(consumer);
		names.forEach(e -> {
			Log.log(e);
		});

		consumer = Fallout::defineConsumer;
		names.forEach(consumer);
		names.forEach(Fallout::defineConsumer);
		names.forEach(System.out::println);

	}

	public void functionalInterfaceTest() {
		BiConsumer<String, Integer> biConsumer = (name, age) -> {
			Log.log(name + " - " + age);
		};

		biConsumer.accept("Test", 10);

		Function<Integer, String> function = x -> ("x: " + x);
		Log.log("Function: " + function.apply(3));

		UnaryOperator<Integer> unaryOperator = x -> (x * x);
		Log.log("UnaryOperator: " + unaryOperator.apply(911));

		BiFunction<String, Integer, String> biFunction = (x, y) -> ("Sum:" + x + y);
		Log.log(biFunction.apply("XDrive", 5));

		BinaryOperator<Integer> binaryOperations = (a, b) -> (a * b + a);
		Log.log(binaryOperations.apply(5, 4));

		Predicate<Integer> predicate = a -> (a > 3);
		Log.log(predicate.test(6));

		BiPredicate<Integer, Integer> biPredicate = (x, y) -> (x > y);
		Log.log(biPredicate.test(101, 91));

		Supplier<List<String>> supplier = () -> new ArrayList<>();
		List<String> list = supplier.get();
		list.add("Emre");
		list.add("Yalcin");
		List<String> list1 = supplier.get();
		list1.add("YLC");

	}

	public void functionalReferenceTest() {

		List<Integer> numbers = Arrays.asList(2, 5, 8, 4, 1);
		// Collections.sort(numbers, new Comparator<Integer>() {
		// @Override
		// public int compare(Integer o1, Integer o2) {
		// return o1 - o2;
		// }
		// });

		Collections.sort(numbers, (o1, o2) -> o2 - o1);

		numbers.forEach(System.out::println);

	}

	public static void main(String[] args) {
		ConsumerTest consumerTest = new ConsumerTest();
		// consumerTest.consumerWithLambda();
		// consumerTest.functionalInterfaceTest();
		consumerTest.functionalReferenceTest();
	}

	static class Fallout {
		public static void defineConsumer(String e) {
			Log.log(e);
		}
	}

}
