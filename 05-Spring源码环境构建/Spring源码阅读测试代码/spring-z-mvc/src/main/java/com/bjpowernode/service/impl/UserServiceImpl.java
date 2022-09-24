package com.bjpowernode.spring.service.impl;

import com.bjpowernode.spring.dao.UserInfoDAO;
import com.bjpowernode.spring.model.UserInfo;
import com.bjpowernode.spring.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userServiceImpl")
public class UserServiceImpl implements UserInfoService {

	@Autowired
	private UserInfoDAO userInfoDAOImpl;

	public UserInfo getuser() {
		return userInfoDAOImpl.getuser();
	}
}