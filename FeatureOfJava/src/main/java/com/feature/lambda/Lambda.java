package com.feature.lambda;

import com.feature.log.Log;

public class Lambda {

	public int f(int a, int b, Base baseOperation) {
		return baseOperation.call(a, b) * 2;
	}

	public int f2(Foo foo, int a, int b) {
		return foo.apply(a, b);
	}

	public static void main(String[] args) {
		Lambda lambda = new Lambda();
		int result = lambda.f(3, 3, (a, b) -> a * b);
		Log.log(result);

		Foo foo = (x, y) -> 2 * x + y;
		result = foo.apply(2, 3);
		Log.log(result);

		result = lambda.f2((x, y) -> (3 * x + y), 1, 2);
		Log.log(result);

	}

}

interface Base {
	public int call(int a, int b);
}

interface Foo {
	public int apply(int a, int b);
}
