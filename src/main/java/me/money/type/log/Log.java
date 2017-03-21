package me.money.type.log;

import org.apache.commons.lang.StringUtils;

public class Log {
	public static void log(Object... objects) {
		System.err.println(StringUtils.join(objects, " "));
	}
}
