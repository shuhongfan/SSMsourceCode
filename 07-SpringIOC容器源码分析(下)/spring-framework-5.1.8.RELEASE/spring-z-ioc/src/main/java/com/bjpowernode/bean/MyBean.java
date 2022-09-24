package com.bjpowernode.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public class MyBean {

	private String myName;

	private int myAge;

	public String getMyName() {
		return myName;
	}

	public void setMyName(String myName) {
		this.myName = myName;
	}

	public int getMyAge() {
		return myAge;
	}

	public void setMyAge(int myAge) {
		this.myAge = myAge;
	}

	/**
	 * 业务方法
	 */
	public void sayHello(String name) {
		System.out.println("MyBean-->sayHello......," + name);
	}
}