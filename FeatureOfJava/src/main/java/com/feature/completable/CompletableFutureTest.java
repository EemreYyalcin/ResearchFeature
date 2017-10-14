package com.feature.completable;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.feature.log.Log;

public class CompletableFutureTest {

	public static void completableTestBasicTask() {
		Stream<CompletableFuture<Void>> futureStream = createFuture().stream();

		Log.log("main 1");
		futureStream.forEach(e -> {
			e.join();
		});
	}

	public static void waitAllTask() {
		List<CompletableFuture<Void>> futureList = createFuture();
		CompletableFuture<Void> allOf = CompletableFuture.allOf((CompletableFuture<?>[]) futureList.toArray());
		allOf.join(); /// waiting all future

	}

	public static void waitAnyTask() {
		List<CompletableFuture<Void>> futureList = createFuture();
		CompletableFuture<Object> anyOf = CompletableFuture.anyOf((CompletableFuture<?>[]) futureList.toArray());
		anyOf.join(); /// waiting faster run

	}

	public static void waitResultInTask() {

		CompletableFuture<List<Path>> pathList = CompletableFuture.supplyAsync(() -> {
			Stream<Path> listStream = Stream.of();
			try {
				listStream = Files.list(Paths.get("\\"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			return listStream.collect(Collectors.toList());
		});

		pathList.join().stream().forEach(e -> {
			Log.log(e.getFileName());
		});
		// pathList.thenAccept(Log::log);

	}

	public static void handleTask() {

		CompletableFuture.supplyAsync(() -> {
			Stream<Path> listStream = Stream.of();
			try {
				listStream = Files.list(Paths.get("\\"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			return listStream.collect(Collectors.toList());
		}).handleAsync((paths, throwable) -> {

			try {
				return paths.stream().map(e -> getFileSize(e));
			} catch (Exception e2) {
				throw new RuntimeException();
			}
		}).thenAccept(map -> {
			map.forEach(Log::log);
		});

	}

	private static Long getFileSize(Path path) {
		try {
			return Files.size(path);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static List<CompletableFuture<Void>> createFuture() {
		CompletableFuture<Void> feture1 = CompletableFuture.runAsync(() -> {
			try {
				Log.log("feture 1");
				Thread.sleep(10000);
				Log.log("feture 1.1");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

		CompletableFuture<Void> feture2 = CompletableFuture.runAsync(() -> {
			try {
				Log.log("feture 2");
				Thread.sleep(5000);
				Log.log("feture 2.1");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		return Arrays.asList(feture1, feture2);

	}

	public static void main(String[] args) {
		// CompletableFutureTest.completableTestBasicTask();
		// CompletableFutureTest.waitAllTask();
		// CompletableFutureTest.waitAnyTask();
		// CompletableFutureTest.waitResultInTask();
		CompletableFutureTest.handleTask();
	}

}
