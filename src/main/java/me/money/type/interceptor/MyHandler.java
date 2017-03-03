package me.money.type.interceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyHandler implements InvocationHandler {

	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Object o = method.invoke(Say.class, args);
		return o;
	}

}
