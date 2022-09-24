package com.bjpowernode.aop;

import com.bjpowernode.aop.config.Config;
import com.bjpowernode.aop.service.SmsService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {

	public static void main(String[] args) {

		//注解版本的方法
		ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);

		//SmsServiceImpl smsService = context.getBean("smsServiceImpl", SmsServiceImpl.class);

		SmsService smsService = context.getBean(SmsService.class);

		smsService.sendSms();
	}
}
