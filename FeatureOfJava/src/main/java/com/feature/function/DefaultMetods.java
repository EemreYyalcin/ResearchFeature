package com.feature.function;

import com.feature.log.Log;

public class DefaultMetods implements TestInterface, TestInterface1 {

	public static void main(String[] args) {
		DefaultMetods defaultMetods = new DefaultMetods();
		defaultMetods.f();
		defaultMetods.g();

		TestInterface testInterface = () -> Log.log("Test Interface method is g() function !!");
		testInterface.g();
		

	}

	@Override
	public void g() {
		Log.log("G called ");
	}

	@Override
	public void f() {
		TestInterface1.super.f();
	}

}

interface TestInterface {

	default void f() {
		Log.log("F called ");
	}

	void g();
}

interface TestInterface1 {

	default void f() {
		Log.log("F1 called ");
	}

	void g();
}