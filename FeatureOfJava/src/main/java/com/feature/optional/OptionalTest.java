package com.feature.optional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.feature.log.Log;

public class OptionalTest {

	public void optionalTest() {

		try {
			Optional<Double> empty = Optional.empty();
			Log.log(empty.get());
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			Optional<String> of = Optional.of("Hey There");
			Log.log(of.get());
			of.ifPresent(e -> {
				Log.log(e);
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			Optional<String> ofNull = Optional.of(null);
			ofNull.ifPresent(e -> {
				Log.log(e);
			});
			Log.log(ofNull.get());
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			Optional<String> ofNullable = Optional.ofNullable(null);
			Log.log(ofNullable.get());
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			Integer num = null;
			Optional<Integer> optional = Optional.ofNullable(num);
			optional.map(e -> Math.pow(e, 3)).ifPresent(Log::log);

		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			String message = null;
			Optional<String> optional = Optional.ofNullable(message);
			optional.filter(e -> e.length() > 3).ifPresent(Log::log);
			Log.log(optional.orElse("MessageIsNull"));

		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			List<String> names = Arrays.asList("Ali", "Ayse", "NOT");
			Optional<List<String>> optional = Optional.ofNullable(names);
			names = optional.orElseGet(() -> new ArrayList<>());
			names = optional.orElseGet(ArrayList::new);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			List<String> names = null;
			Optional<List<String>> optional = Optional.ofNullable(names);
			names = optional.orElseThrow(() -> new RuntimeException());
			names = optional.orElseThrow(RuntimeException::new);
			

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		OptionalTest test = new OptionalTest();
		test.optionalTest();

	}

}
