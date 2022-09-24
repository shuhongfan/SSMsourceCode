package com.bjpowernode.spring.controller;

import com.bjpowernode.spring.model.UserInfo;
import com.bjpowernode.spring.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserInfoController {

	@Autowired
	private UserInfoService userServiceImpl;

	@RequestMapping("/mvc/userInfo")
	public String userInfo(Model model) {

		UserInfo userInfo = userServiceImpl.getuser();
		model.addAttribute("userInfo", userInfo);

		return "userInfo";
	}

	@RequestMapping("/mvc/userList")
	public String userList(Model model, @RequestBody String ss, @RequestBody String bb, @RequestParam("name") String name, @RequestParam("phone") String phone) {

		UserInfo userInfo = userServiceImpl.getuser();
		model.addAttribute("userList", userInfo);

		System.out.println(name);
		System.out.println(phone);
		return "userList";
	}

	@RequestMapping("/mvc/userJson")
	public @ResponseBody String userJson(Model model, @RequestBody String ss, @RequestBody String bb, @RequestParam("name") String name, @RequestParam("phone") String phone) {

		UserInfo userInfo = userServiceImpl.getuser();
		model.addAttribute("userList", userInfo);

		System.out.println(name);
		System.out.println(phone);
		return "userList";
	}
}