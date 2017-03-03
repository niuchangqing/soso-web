package me.money.type.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class TestProxy {
	public static void main(String[] args) {
		Person person = new User();

		InvocationHandler handler = new MyProxy(person);

		person = (Person) Proxy.newProxyInstance(handler.getClass().getClassLoader(), person.getClass().getInterfaces(),
				handler);
		
		person.say();

		person.walk();
	}
}
