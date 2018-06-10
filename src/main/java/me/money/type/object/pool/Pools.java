package me.money.type.object.pool;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Pools {
	public static final ExecutorService es = Executors.newFixedThreadPool(10);

	public static void submit(Runnable task) {
		es.submit(task);
	}

	public static <T> Future<T> submit(Callable<T> callable) {
		return es.submit(callable);
	}

	public static void shutdown() {
		es.shutdown();
	}
}
