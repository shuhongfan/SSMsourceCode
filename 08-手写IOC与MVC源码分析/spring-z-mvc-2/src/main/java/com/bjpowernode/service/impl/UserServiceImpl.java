package com.bjpowernode.service.impl;

import com.bjpowernode.dao.UserInfoDAO;
import com.bjpowernode.model.UserInfo;
import com.bjpowernode.service.UserInfoService;
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