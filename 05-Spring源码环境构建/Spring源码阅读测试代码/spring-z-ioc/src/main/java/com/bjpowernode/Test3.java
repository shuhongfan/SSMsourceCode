package com.bjpowernode;

import java.lang.reflect.Method;

public class Test3 {

	public static void main(String[] args) throws Exception {

		Class<?> clazz = Class.forName("com.bjpowernode.bean.MyBean");

		Method method = clazz.getDeclaredMethod("setheBean", clazz.getDeclaredField("heBean").getType());
		method.setAccessible(true);

		Object object = clazz.getDeclaredConstructor().newInstance();

		/*
		HeBean heBean = new HeBean(null);
		heBean.setHeAge(11);
		heBean.setHeName("23424");

		method.invoke(object, heBean);

		System.out.println(object);*/
	}
}