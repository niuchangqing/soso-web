package me.money.type.callable;

import me.money.type.utils.TimePause;

public class TestCallAble {
	public static void main(String[] args) throws Exception {
		int c = 0;
		while (c++ < 100) {
			TimePause.pause(1);
			System.err.println("主线程" + c);
			if (c == 5) {
				
			}
		}
	}
}
