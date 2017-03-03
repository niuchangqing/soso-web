package me.money.type.thread;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.google.common.collect.Lists;

public class MoreThreads {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		// for (int i = 0; i < 10; i++) {
		// new Thread(new LiftOff()).start();;
		// }
		// System.out.println("Waiting for LiftOff!");

		// ExecutorService e = Executors.newCachedThreadPool();
		ExecutorService e = Executors.newFixedThreadPool(10);

		// for (int i = 0; i < 100; i++) {
		// e.execute(new LiftOff());
		// }
		//
		// e.shutdown();
		// System.out.println("end------------");

		List<Future<String>> list = Lists.newArrayList();
		for (int i = 0; i < 100; i++) {
			list.add(e.submit(new TaskWithResults(i)));
		}

		CountDownLatch c = new CountDownLatch(100);
		while (c.getCount() > 0) {
			for (Future<String> f : list) {
				if (f.isDone()) {
					c.countDown();
					System.err.println(f.isDone());
					String rs = f.get();
					System.err.println(rs);
				} else {
					System.err.println(f.isDone());
				}
			}
		}

		e.shutdown();
	}
}
