package com.bjpowernode.aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SmsAspect {

	@Pointcut("execution(public * com.bjpowernode.aop.service..*.*(..))")
	public void pointcut() {
	}

	@Before("pointcut()")
	public void before() {
		System.out.println("before..........");
	}

	@After("pointcut()")
	public void after() {
		System.out.println("after..........");
	}

	@Around("pointcut()")
	public Object around(ProceedingJoinPoint proceedingJoinPoint) {

		System.out.println("around start..........");

		long startTime = System.currentTimeMillis();
		Object object = null;
		try {
			//执行真正的service里面的方法
			object = proceedingJoinPoint.proceed();
		} catch (Throwable throwable) {
			throwable.printStackTrace();
		}
		long endTime = System.currentTimeMillis();

		System.out.println("around end, exe time: " + (endTime - startTime) + " ms");
		return object;
	}
}