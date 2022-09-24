package com.bjpowernode;

import com.bjpowernode.ioc.context.MyAnnotationConfigApplicationContext;
import com.bjpowernode.ioc.context.MyApplicationContext;
import com.bjpowernode.sample.config.Config;
import com.bjpowernode.sample.service.SmsService;

public class Test {

	public static void main(String[] args) {

		//注解版本的方法
		MyApplicationContext context = new MyAnnotationConfigApplicationContext(Config.class);

		SmsService smsService = context.getBean("smsServiceImpl", SmsService.class);
		//SmsServiceImpl smsService = context.getBean("smsServiceImpl", SmsServiceImpl.class);
		smsService.sendSms();

		//System.out.println("---------------------------------------------------");

		//MyBean myBean = context.getBean("myBeanImpl", MyBean.class);
		//myBean.sayHi();
	}
}