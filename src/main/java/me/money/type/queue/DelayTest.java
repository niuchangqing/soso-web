package me.money.type.queue;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayTest {

	public static void main(String... args) throws InterruptedException {
		DelayQueue<Delayed> dq = new DelayQueue<Delayed>();
		DeleyedTest ob1 = new DeleyedTest(10, "two");
		DeleyedTest ob2 = new DeleyedTest(5, "one");
		DeleyedTest ob3 = new DeleyedTest(15, "three");

		dq.add(ob1);
		dq.add(ob2);
		dq.add(ob3);

		while (true) {
			DeleyedTest s = (DeleyedTest) dq.take();
			System.out.println(s.get());
		}
	}
}

class DeleyedTest implements Delayed {
	private long deleyTime = 0;
	private String data;

	DeleyedTest(long deleyTime, String data) {
		this.deleyTime = System.currentTimeMillis() + deleyTime * 1000;
		this.data = data;
	}

	public String get() {
		return data;
	}

	@Override
	public int compareTo(Delayed ob) {
		if (this.deleyTime < ((DeleyedTest) ob).deleyTime) {
			return -1;
		}
		if (this.deleyTime > ((DeleyedTest) ob).deleyTime) {
			return 1;
		}
		return 0;
	}

	@Override
	public long getDelay(TimeUnit unit) {
		return unit.convert(deleyTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
	}

}
