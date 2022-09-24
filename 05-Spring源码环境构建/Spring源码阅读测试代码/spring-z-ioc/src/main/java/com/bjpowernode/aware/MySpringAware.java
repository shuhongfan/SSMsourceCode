package com.bjpowernode.aware;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class MySpringAware implements BeanNameAware, BeanFactoryAware, BeanClassLoaderAware, ApplicationContextAware {

	private String beanName;

	private BeanFactory beanFactory;

	private ClassLoader classLoader;

	private ApplicationContext applicationContext;

	@Override
	public void setBeanName(String name) {
		System.out.println("调用了 BeanNameAware 的 setBeanName 方法");
		this.beanName = name;
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		System.out.println("调用了 BeanFactoryAware 的 setBeanFactory 方法");
		this.beanFactory = beanFactory;
	}

	@Override
	public void setBeanClassLoader(ClassLoader classLoader) {
		System.out.println("调用了 BeanClassLoaderAware 的 setBeanClassLoader 方法");
		this.classLoader = classLoader;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		System.out.println("调用了 ApplicationContextAware 的 setApplicationContext 方法");
		this.applicationContext = applicationContext;
	}

	public void display(){
		System.out.println("beanName:" + beanName);
		System.out.println("beanFactory：" + beanFactory.toString());
		System.out.println("classLoader：" + classLoader.toString());
		System.out.println("applicationContext：" + applicationContext.toString());
	}
}