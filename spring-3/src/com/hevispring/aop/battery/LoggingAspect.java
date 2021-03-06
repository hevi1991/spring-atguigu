package com.hevispring.aop.battery;

import java.util.Arrays;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

//可以使用Order来设定切面的优先级，数值越小，越先处理切面。顺序遵循栈式，先进后出，后进先出
@Order(2)
@Aspect
@Component
public class LoggingAspect {
	
	
	/**
	 * 定义一个方法， 用于声明切入点表达式。一般该方法中不需要填入其他的代码
	 * 使用@Pointcut来声明切入点表达式
	 * 若AOP声明需要使用此表达式时，用此方法名替代
	 */
	@Pointcut("execution(* com.hevispring.aop.battery.Calculate.*(..))")
	public void defineJoinPointExpression() {}
	
	
	
	//可参照LoggingProxy了解出发时机
	//前置通知
	@Before("defineJoinPointExpression()")
	public void before(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		List<Object> args = Arrays.asList(joinPoint.getArgs());
		System.out.println(methodName+" ("+args+")-beginning");
	}
	//后置通知
	@After("defineJoinPointExpression()")
	public void after(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		List<Object> args = Arrays.asList(joinPoint.getArgs());
		System.out.println(methodName+" ("+args+")-ending");
	}
	//返回通知
	@AfterReturning(value="defineJoinPointExpression()",returning="result")
	public void afterReturning(JoinPoint joinPoint, Object result) {
		String methodName = joinPoint.getSignature().getName();
		List<Object> args = Arrays.asList(joinPoint.getArgs());
		System.out.println(methodName+" ("+args+")-return:"+result);
	}
	//异常通知
	@AfterThrowing(value="defineJoinPointExpression()",throwing="err")
	public void afterThrowing(JoinPoint joinPoint, Exception err) {
		String methodName = joinPoint.getSignature().getName();
		List<Object> args = Arrays.asList(joinPoint.getArgs());
		System.out.println(methodName+" ("+args+")-throw error:"+err);
	}
	//环绕通知
	@Around(value="defineJoinPointExpression()")
	public Object around(ProceedingJoinPoint proceedingJoinPoint) {
		Object result = null;
		try {
			result = proceedingJoinPoint.proceed();
			System.out.println(proceedingJoinPoint.getSignature().getName()+ " runs with " + Arrays.asList(proceedingJoinPoint.getArgs()) + " = " + result);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
}
