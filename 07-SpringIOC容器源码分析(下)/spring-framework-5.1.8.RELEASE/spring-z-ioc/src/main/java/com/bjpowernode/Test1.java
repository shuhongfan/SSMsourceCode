package com.bjpowernode;

import com.bjpowernode.bean.MyBean;
import com.bjpowernode.config.SpringConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test1 {

	public static void main(String[] args) {
		//启动spring ioc容器:
		//1、基于xml配置方式
		//2、基于注解的配置方式
		ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

		MyBean myBean = context.getBean(MyBean.class);

		myBean.sayHello("张三丰");
	}
}