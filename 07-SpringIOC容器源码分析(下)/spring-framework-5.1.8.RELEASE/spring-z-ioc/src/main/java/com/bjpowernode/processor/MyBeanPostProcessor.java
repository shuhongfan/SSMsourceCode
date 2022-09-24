package com.bjpowernode.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class MyBeanPostProcessor implements BeanPostProcessor {

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("Bean [" + beanName + "] 开始初始化......");
		// 这里一定要返回 bean，不能返回 null
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		//System.out.println("Bean [" + beanName + "] 完成初始化......");
		// 这里一定要返回 bean，不能返回 null

		if (beanName.equals("myBean")) {
			System.out.println("Bean [" + beanName + "] 完成初始化......");
			//对bean的增强
		}
		return bean;
	}
}