package me.money.type.concurrent;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class TestCyclicBarry {

	private static AtomicInteger ai = new AtomicInteger(0);

	public static void main(String[] args) throws Exception {
		// testDelayQueue();
		testCyclicBarrier();
	}

	private static void testCyclicBarrier() {

		final CyclicBarrier cb = new CyclicBarrier(10);

		for (int i = 0; i < 10; i++) {
			es.submit(new Runnable() {
				@Override
				public void run() {
					try {
						TimeUnit.SECONDS.sleep(new Random().nextInt(10));
						int name = ai.incrementAndGet();
						System.out.println("[ " + name + " ]到达");
						cb.await();

						System.out.println("出发");
						TimeUnit.SECONDS.sleep(new Random().nextInt(10));
						System.out.println(name + "完毕");
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (BrokenBarrierException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
		}
		es.shutdown();
	}

	private static ExecutorService es = Executors.newFixedThreadPool(10);

}