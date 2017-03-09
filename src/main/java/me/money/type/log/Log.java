package me.money.type.log;

public class Log {

	public static void log(Object... objects) {
		for (Object object : objects) {
			System.err.println(object);
		}
	}

	public static void logSingleLine(Object... objects) {
		String space = "";
		for (Object object : objects) {
			System.err.print(space);
			System.err.print(object);
			space = " ";
		}
		System.err.println();
	}
}
