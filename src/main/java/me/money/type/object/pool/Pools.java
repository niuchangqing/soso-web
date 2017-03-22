package me.money.type.object.pool;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author Niucqing
 * @email niucqing@gmail.com
 */
public class Pools {
	private static final ExecutorService es = Executors.newFixedThreadPool(10);
//	private static final ExecutorService ess = Executors.newCachedThreadPool();

	/**
	 * submit 有返回值，如果过程有异常，可以通过future.get()重新捕获异常
	 * @param task
	 */
	public static Future<?> submit(Runnable task) {
		return es.submit(task);
	}

	/**
	 * execute 没有返回值，如果有异常，无法在最外层捕获
	 * @param task
	 */
	public static void execute(Runnable task) {
		es.execute(task);
	}

	public static <T> Future<T> submit(Callable<T> callable) {
		return es.submit(callable);
	}

	/**
	 * 等待所有任务执行完毕之后执行shutdown
	 * 建议使用
	 */
	public static void shutdown() {
		es.shutdown();
	}

	/**
	 * 正在执行中的任务也会停止
	 * 不建议使用
	 */
	public static void shutdownNow() {
		es.shutdownNow();
	}
}
