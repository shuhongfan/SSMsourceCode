package com.bjpowernode.sample.controller;

import com.bjpowernode.ioc.annotation.MyAutowired;
import com.bjpowernode.mvc.annotation.MyController;
import com.bjpowernode.mvc.annotation.MyRequestMapping;
import com.bjpowernode.mvc.annotation.MyRequestParam;
import com.bjpowernode.mvc.ui.MyModel;
import com.bjpowernode.sample.model.UserInfo;
import com.bjpowernode.sample.service.UserInfoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@MyController
public class UserInfoController {

	@MyAutowired
	private UserInfoService userServiceImpl;

	@MyRequestMapping("/mvc/userList")
	public String userList(MyModel model,
						   @MyRequestParam("name") String name,
						   @MyRequestParam("phone") String phone) {

		UserInfo userInfo = userServiceImpl.getuser();
		model.addAttribute("userList", userInfo);

		System.out.println(name);
		System.out.println(phone);
		return "userList";
	}

	@MyRequestMapping("/mvc/userInfo")
	public String userInfo(MyModel model, HttpServletRequest request, HttpServletResponse response,
						   @MyRequestParam("name") String name, @MyRequestParam("phone") String phone) {

		System.out.println("请求的参数是: name=" + name + ", phone=" + phone);
		UserInfo userInfo = userServiceImpl.getuser();

		model.addAttribute("userInfo", userInfo);

		return "userInfo";
	}
}