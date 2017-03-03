package me.money.type.interceptor;

import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class MyInterceptor implements MethodInterceptor {

	public Object invoke(MethodInvocation invocation) throws Throwable {
		Method m = invocation.getMethod();

		System.out.println(m.getName() + "----方法执行了");

		return null;
	}

}
