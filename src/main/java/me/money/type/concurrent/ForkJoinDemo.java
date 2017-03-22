package me.money.type.concurrent;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

import com.google.common.base.Stopwatch;

import me.money.type.log.Log;

/**
 * @author Niucqing
 * @email niucqing@gmail.com
 */
public class ForkJoinDemo extends RecursiveTask<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int THRESHOLD = 10;
	private int start;
	private int end;

	public ForkJoinDemo(int start, int end) {
		this.start = start;
		this.end = end;
	}

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		Stopwatch sw = Stopwatch.createStarted();
		ForkJoinDemo d = new ForkJoinDemo(0, 10000000);
		ForkJoinPool fjp = new ForkJoinPool();
		ForkJoinTask<Long> result = fjp.submit(d);
		Log.log(result.get());
		long ms = sw.elapsed(TimeUnit.MILLISECONDS);
		Log.log("use time", ms, "ms");
	}

	@Override
	protected Long compute() {
		long sum = 0;
		if ((end - start) <= THRESHOLD) {
			for (int i = start; i <= end; i++) {
				sum += i;
			}
		} else {
			int mid = (start + end) / 2;
			ForkJoinDemo left = new ForkJoinDemo(start, mid);
			ForkJoinDemo right = new ForkJoinDemo(mid + 1, end);
			left.fork();
			right.fork();

			Long l = left.join();
			Long r = right.join();
			sum = l + r;
		}
		return sum;
	}

}
