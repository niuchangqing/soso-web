package me.money.type.log;

public class Log {

	public static void log(Object... objects) {
		for (Object object : objects) {
			System.err.println(object);
		}
	}

}
