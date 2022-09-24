package com.bjpowernode;

import com.bjpowernode.config.Config;
import com.bjpowernode.service.SmsService;
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