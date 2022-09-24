package com.bjpowernode.sample.service.impl;

import com.bjpowernode.sample.service.SmsService;
import com.bjpowernode.ioc.annotation.MyService;

@MyService
public class SmsServiceImpl implements SmsService {

	public void sendSms() {
		System.out.println("业务方法执行，sendSms: spring aop......");
	}
}