package me.money.type.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyProxy implements InvocationHandler {

	private Object proxyObject;

	public MyProxy(Object o) {
		this.proxyObject = o;
	}

	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("开始执行代理方法了");
		System.out.println("go------");
		method.invoke(proxyObject, args);
		System.out.println(method.getName() + "执行完毕");
		return null;
	}

}
