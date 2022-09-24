package com.bjpowernode.sample.aspect;

import com.bjpowernode.aop.annotation.*;
import com.bjpowernode.aop.aop.MyProceedingJoinPoint;
import com.bjpowernode.ioc.annotation.MyComponent;

@MyAspect
@MyComponent
public class SmsAspect {

	@MyPointcut(value = "public .* com.bjpowernode.sample.service.*.*(.*)")
	public void pointcut() {
	}

	@MyPointcut(value = "public .* com.bjpowernode.sample.bean.*.*(.*)")
	public void pointcut2() {
	}

	//@MyBefore(value = "pointcut()")
	public void before() {
		System.out.println("前置通知，before..........");
	}

	@MyAfter(value = "pointcut()")
	public void after() {
		System.out.println("后置通知，after..........");
	}

	@MyAround(value = "pointcut()")
	public Object around(MyProceedingJoinPoint myProceedingJoinPoint) {

		System.out.println("环绕通知，around start..........");

		Object object = null;
		try {
			//执行真正的service里面的方法
			object = myProceedingJoinPoint.proceed();
		} catch (Throwable throwable) {
			throwable.printStackTrace();
		}

		System.out.println("环绕通知，around end..........");
		return object;
	}
}