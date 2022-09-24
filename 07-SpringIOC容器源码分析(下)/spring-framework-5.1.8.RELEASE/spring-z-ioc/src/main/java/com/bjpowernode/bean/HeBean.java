package com.bjpowernode.bean;

public class HeBean {

	private String heName;

	private int heAge;

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