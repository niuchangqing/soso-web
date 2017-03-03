package me.money.type.thread;

public class SimpleThread extends Thread {
	private int countDown = 5;
	private static int threadCount = 0;

	public SimpleThread() {
		super(Integer.toString(++threadCount));
		start();
		this.setName("niu");
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "#" + getName() + "(" + countDown + ")";
	}

	@Override
	public void run() {
		while (true) {
			System.err.println(this);
			if (--countDown == 0)
				return;
		}
	}

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			new SimpleThread();
		}
	}
}
