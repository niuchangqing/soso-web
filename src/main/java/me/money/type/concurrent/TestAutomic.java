package me.money.type.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class TestAutomic {
	public static void main(String[] args) {
		new MT().start();
		new MT().start();
		new MT().start();
		new MT().start();
	}
}

class Student {
	static AtomicInteger ai = new AtomicInteger(0);

	public static void add() {
		int v = ai.incrementAndGet();
		System.out.println(v);
	}
}


class MT extends Thread{
	@Override
	public void run() {
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < 100; i++) {
			Student.add();
		}
		
	}
}
