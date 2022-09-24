package com.bjpowernode.initializing;

import org.springframework.beans.factory.InitializingBean;

public class MyInitializingBean implements InitializingBean {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		this.name = "张三丰00001";
		System.out.println("afterPropertiesSet..................");
	}
}