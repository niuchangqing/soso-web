package me.money.type.log;

import org.apache.commons.lang.StringUtils;

public class Log {
	private static final String LINESEPRATOR = System.lineSeparator();

	public static void log(Object... objects) {
		System.err.println(StringUtils.join(objects, LINESEPRATOR));
	}

	public static void logSingleLine(Object... objects) {
		System.err.println(StringUtils.join(objects, " "));
	}
}
