package com.bjpowernode.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HeBean {

	private String heName;

	private int heAge;

	@Autowired
	private MyBean myBean;

	public String getHeName() {
		return heName;
	}

	public void setHeName(String heName) {
		this.heName = heName;
	}

	public int getHeAge() {
		return heAge;
	}

	public void setHeAge(int heAge) {
		this.heAge = heAge;
	}

	public void sayHello(String name) {
		System.out.println("HeBean-->sayHello......," + name);
	}
}