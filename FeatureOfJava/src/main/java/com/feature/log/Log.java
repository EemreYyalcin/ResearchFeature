package com.feature.log;

import java.util.Arrays;
import java.util.List;

public class Log {

	public static void log(Object...message){
		List<Object> array = Arrays.asList(message);
		array.forEach(e -> {System.out.print(e.toString() + " ");});
		System.out.println();
	}
	
	
}
