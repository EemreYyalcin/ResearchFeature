package com.feature.notation;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.util.function.Consumer;

import com.feature.log.Log;
import com.feature.notation.Notation4.Priority;

public class Notation {

	public static void main(String[] args) {

		Consumer<Class<?>> consumerObj = e -> {
			if (e.isAnnotationPresent(Notation2.class)) {
				Annotation annotation = e.getAnnotation(Notation2.class);
				Notation2 notation2 = (Notation2) annotation;
				Log.log(e, notation2.value());
			}
		};
		
		
		Consumer<Class<?>> consumerMethodObj = e -> {
			for (Method method : e.getDeclaredMethods()) {
				if (method.isAnnotationPresent(Notation1.class)) {
					Notation1 notation1 = method.getAnnotation(Notation1.class);
					Log.log(e, method, notation1.value());
				}
				if (method.isAnnotationPresent(Notation1.class)) {
					Notation1 notation1 = method.getAnnotation(Notation1.class);
					Log.log(e, method, notation1.value());
				}
			}
		};

		consumerObj.accept(Ex1.class);
		consumerMethodObj.accept(Ex1.class);

		consumerObj.accept(Ex3.class);
		consumerMethodObj.accept(Ex3.class);
		
	}

}

@Notation2(value = "Ex1")
class Ex1 {

	String ex = "Test1";

	@Notation2("f()")
	public void f() {

	}

	@Notation1("g()")
	public void g() {

	}
}

@Notation3(value = { @Notation2("Hey"), @Notation2("there") })
class Ex2 {

}

@Notation4(priority = Priority.HIGH, tags = { "x", "y" })
class Ex3 {

	@Notation2("f()")
	public void f() {

	}

	@Notation1("g()")
	public void g() {

	}

}

@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@interface Notation1 {
	String value();
}

@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@interface Notation2 {
	String value();
}

@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@interface Notation3 {
	Notation2[] value();
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE) // on class level
@interface Notation4 {

	public enum Priority {
		LOW, MEDIUM, HIGH
	}

	Priority priority() default Priority.MEDIUM;

	String[] tags() default "Default Tag";

	String createdBy() default "EmreYLC";

	String lastModified() default "YLC";

}
