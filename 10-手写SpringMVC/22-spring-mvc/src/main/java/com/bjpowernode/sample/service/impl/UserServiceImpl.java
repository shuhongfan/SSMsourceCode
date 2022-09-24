package com.bjpowernode.sample.service.impl;

import com.bjpowernode.ioc.annotation.MyAutowired;
import com.bjpowernode.ioc.annotation.MyService;
import com.bjpowernode.sample.dao.UserInfoDAO;
import com.bjpowernode.sample.model.UserInfo;
import com.bjpowernode.sample.service.UserInfoService;

@MyService
public class UserServiceImpl implements UserInfoService {

	@MyAutowired
	private UserInfoDAO userInfoDAOImpl;

	public UserInfo getuser() {
		return userInfoDAOImpl.getuser();
	}
}