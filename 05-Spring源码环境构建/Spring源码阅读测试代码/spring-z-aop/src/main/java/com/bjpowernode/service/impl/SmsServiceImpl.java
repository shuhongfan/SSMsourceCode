package com.bjpowernode.service.impl;

import com.bjpowernode.service.SmsService;
import org.springframework.stereotype.Service;

@Service
public class SmsServiceImpl implements SmsService {

	public void sendSms() {
		System.out.println("sendSms: spring aop......");
	}
}