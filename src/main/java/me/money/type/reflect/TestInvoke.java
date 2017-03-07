package me.money.type.reflect;

import java.lang.reflect.Method;

import me.money.type.log.Log;

public class TestInvoke {

	public static void main(String[] args) throws Exception {
		
		Class c = Class.forName("me.money.type.reflect.Users");
		Object o = c.newInstance();
		
		Log.log(c.getName(),c.getTypeName(),c.getModifiers(),c.getPackage());
		Log.log(c.getSimpleName());
		
		Method[] ms = c.getDeclaredMethods();
		for (Method method : ms) {
			method.invoke(o, null);
		}
		
	}

}
