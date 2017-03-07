package me.money.type.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

public class TestCyclicBarry {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		CyclicBarrier cb = new CyclicBarrier(3, new Runnable() {

			@Override
			public void run() {
				System.out.println("主线程开始");
			}
		});

		new Cb(cb, "老王").start();
		new Cb(cb, "老牛").start();
		new Cb(cb, "老韩").start();
	}

}

class Cb extends Thread {
	CyclicBarrier c;
	String f;

	public Cb(CyclicBarrier c, String name) {
		this.c = c;
		this.f = name;
	}

	public void run() {
		System.out.println(f + "wating orther...");
		
		try {
			TimeUnit.SECONDS.sleep(2);
			c.await();
		} catch (InterruptedException | BrokenBarrierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}