package me.money.type.reflect;

import java.lang.reflect.Method;

public class TestInvoke {

	public static void main(String[] args) throws Exception {
		
		Class c = Class.forName("me.money.type.reflect.Users");
		Object o = c.newInstance();
		
		Method[] methods = c.getDeclaredMethods();
		for (Method method : methods) {
			if(method.getName().equals("buy")){
				method.invoke(o, null);
			}
		}
	}

}
