package com.bjpowernode.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * spring整合了eclipse下的一个aspcetj项目实现aop功能
 *
 */
@Aspect
@Component
public class SmsAspect {

	@Pointcut("execution(public * com.bjpowernode.service..*.*(..))")
	public void pointcut() {
	}

	/*@Pointcut("execution(public * com.bjpowernode.biz..*.*(..))")
	public void pointcutBiz() {
	}

	@Before("pointcutBiz()")
	public void beforeBiz() {
		System.out.println("beforeBiz..........");
	}*/

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
			//TODO 执行真正的service里面的方法
			object = proceedingJoinPoint.proceed();
		} catch (Throwable throwable) {
			throwable.printStackTrace();
		}
		long endTime = System.currentTimeMillis();

		System.out.println("around end, exe time: " + (endTime - startTime) + " ms");
		return object;
	}
}