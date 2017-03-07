package me.money.type.callable;

import me.money.type.utils.TimeStop;

public class TestCallAble {
	public static void main(String[] args) throws Exception {
		int c = 0;
		while (c++ < 100) {
			TimeStop.pause(1);
			System.err.println("主线程" + c);
			if (c == 5) {
				
			}
		}
	}
}
