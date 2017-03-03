package me.money.type.utils;

import java.util.concurrent.TimeUnit;

public class TimePause {
	public static void pause(long timeout) {
		try {
			TimeUnit.SECONDS.sleep(timeout);
		} catch (InterruptedException e) {
			System.err.println(e);
		}
	}
}
