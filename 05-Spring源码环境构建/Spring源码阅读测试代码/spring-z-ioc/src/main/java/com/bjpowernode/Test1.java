package com.bjpowernode;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan(basePackages = {"com.bjpowernode.factorybean"})
@Configuration
public class Test1 {

	public static void main(String[] args) {
		//启动spring ioc容器:
		//1、基于xml配置方式 ClassPathXmlApplicationContext
		//2、基于注解的配置方式
		ApplicationContext context = new AnnotationConfigApplicationContext(Test1.class);

		Object myService = context.getBean("myService");
		System.out.println(myService);

		Object myService2 = context.getBean("&myService");
		System.out.println(myService2);

		/*MyBean myBean = context.getBean(MyBean.class);
		myBean.sayHello("张三丰");

		HeBean heBean = context.getBean(HeBean.class);
		heBean.sayHello("张无忌");*/
	}
}