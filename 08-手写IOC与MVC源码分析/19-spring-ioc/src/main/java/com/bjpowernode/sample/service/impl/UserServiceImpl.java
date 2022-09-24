package com.bjpowernode.sample.service.impl;

import com.bjpowernode.sample.dao.UserDAO;
import com.bjpowernode.sample.service.UserService;
import com.bjpowernode.spring.annotation.MyAutowired;
import com.bjpowernode.spring.annotation.MyService;

@MyService
public class UserServiceImpl implements UserService {

	@MyAutowired
	private UserDAO smsDAO;

	public void sendSms() {
		smsDAO.sendSms();
	}
}