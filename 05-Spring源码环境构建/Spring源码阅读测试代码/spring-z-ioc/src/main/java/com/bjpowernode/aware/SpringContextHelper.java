package com.bjpowernode.aware;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringContextHelper implements ApplicationContextAware {

	private static ApplicationContext context = null;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		context = applicationContext;
		System.out.println("Aware接口案例: SpringContextHelper-->ApplicationContextAware->...................................");
	}

	public static Object getBean(String name) {
		return context.getBean(name);
	}
}