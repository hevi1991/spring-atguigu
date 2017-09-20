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

//����ʹ��Order���趨��������ȼ�����ֵԽС��Խ�ȴ������档˳����ѭջʽ���Ƚ����������ȳ�
@Order(2)
@Aspect
@Component
public class LoggingAspect {
	
	
	/**
	 * ����һ�������� ����������������ʽ��һ��÷����в���Ҫ���������Ĵ���
	 * ʹ��@Pointcut��������������ʽ
	 * ��AOP������Ҫʹ�ô˱���ʽʱ���ô˷��������
	 */
	@Pointcut("execution(* com.hevispring.aop.battery.Calculate.*(..))")
	public void defineJoinPointExpression() {}
	
	
	
	//�ɲ���LoggingProxy�˽����ʱ��
	//ǰ��֪ͨ
	@Before("defineJoinPointExpression()")
	public void before(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		List<Object> args = Arrays.asList(joinPoint.getArgs());
		System.out.println(methodName+" ("+args+")-beginning");
	}
	//����֪ͨ
	@After("defineJoinPointExpression()")
	public void after(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		List<Object> args = Arrays.asList(joinPoint.getArgs());
		System.out.println(methodName+" ("+args+")-ending");
	}
	//����֪ͨ
	@AfterReturning(value="defineJoinPointExpression()",returning="result")
	public void afterReturning(JoinPoint joinPoint, Object result) {
		String methodName = joinPoint.getSignature().getName();
		List<Object> args = Arrays.asList(joinPoint.getArgs());
		System.out.println(methodName+" ("+args+")-return:"+result);
	}
	//�쳣֪ͨ
	@AfterThrowing(value="defineJoinPointExpression()",throwing="err")
	public void afterThrowing(JoinPoint joinPoint, Exception err) {
		String methodName = joinPoint.getSignature().getName();
		List<Object> args = Arrays.asList(joinPoint.getArgs());
		System.out.println(methodName+" ("+args+")-throw error:"+err);
	}
	//����֪ͨ
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