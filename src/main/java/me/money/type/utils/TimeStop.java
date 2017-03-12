package me.money.type.utils;

import java.util.concurrent.TimeUnit;

import me.money.type.log.Log;

public class TimeStop {
	public static void second(long timeout) {
		try {
			TimeUnit.SECONDS.sleep(timeout);
			Log.logSingleLine("sleeping ... ", timeout);
		} catch (InterruptedException e) {
			System.err.println(e);
		}
	}

	public static void millSeconds(long timeout) {
		try {
			TimeUnit.MILLISECONDS.sleep(timeout);
			Log.logSingleLine("sleeping ... ", timeout);
		} catch (InterruptedException e) {
			Log.log(e);
		}
	}
}
