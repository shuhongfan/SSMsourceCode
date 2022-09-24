package com.bjpowernode;

import com.bjpowernode.sample.config.Config;
import com.bjpowernode.sample.service.UserService;
import com.bjpowernode.sample.service.impl.UserServiceImpl;
import com.bjpowernode.spring.context.MyAnnotationConfigApplicationContext;
import com.bjpowernode.spring.context.MyApplicationContext;

public class Test {

	public static void main(String[] args) {

		//注解版本的容器
		MyApplicationContext context = new MyAnnotationConfigApplicationContext(Config.class);

		//从容器中获取bean对象
		UserService userService = context.getBean("userService", UserService.class);
		System.out.println(userService);
		userService.sendSms();

		UserServiceImpl userServiceImpl = context.getBean("userServiceImpl", UserServiceImpl.class);
		System.out.println(userServiceImpl);
		userServiceImpl.sendSms();
	}
}