package com.hevispring.aop.helloworld;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;

public class MyLoggingProxy<T> {

	private T target;
	
	
	
	public MyLoggingProxy(T target) {
		super();
		this.target = target;
	}



	@SuppressWarnings("unchecked")
	public T getLoggingProxy() {
		
		ClassLoader classLoader = target.getClass().getClassLoader();
		Class<?>[] interfaces = target.getClass().getInterfaces();
		InvocationHandler handler = new InvocationHandler() {
			
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				
				System.out.println("method:"+method.getName()+"-start-with params+"+Arrays.asList(args).toString());
				
				Object result = method.invoke(target, args);
				
				System.out.println("method:"+method.getName()+"-end");
				
				return result;
			}
		};
		
		return (T) Proxy.newProxyInstance(classLoader, interfaces, handler);
	}
}
