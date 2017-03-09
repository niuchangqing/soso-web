package me.money.type.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import me.money.type.log.Log;
import me.money.type.utils.TimeStop;

public class TestCountDownLatch {
	private static ExecutorService es = Executors.newFixedThreadPool(10);

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		CountDownLatch cd = new CountDownLatch(10);
		List<Future<Long>> list = new ArrayList<Future<Long>>();
		for (int i = 0; i < 10; i++) {
			Future<Long> submit = es.submit(new worker(cd, new Random().nextInt(100)));
			list.add(submit);
		}
		Log.log("主线程开始等待...");
		cd.await();
		Log.log("主线程继续执行...");
		
		long sum = 0;
		for (Future<Long> future : list) {
			sum+=future.get();
		}
		Log.log("sum="+sum);
		
	}
}

class worker implements Callable<Long> {
	private CountDownLatch cd;
	private int v;

	public worker(CountDownLatch cd, int v) {
		this.cd = cd;
		this.v = v;
	}

	@Override
	public Long call() throws Exception {
		long sum = 0;
		for (int i = 0; i < v; i++)
			sum += i;
		TimeStop.second(new Random().nextInt(10));
		cd.countDown();
		Log.log(v+"---"+sum);
		return sum;
	}
}
