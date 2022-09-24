package com.bjpowernode.xml;

import com.bjpowernode.aware.MySpringAware;
import com.bjpowernode.bean.MyBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Test3 {

	public static void main(String[] args) {

		/*ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext();
		context.setConfigLocation("applicationContext.xml");
		context.getEnvironment().setRequiredProperties("aaa");//验证会出问题，有key没有value
		context.refresh();*/

		//ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		//ApplicationContext context = new FileSystemXmlApplicationContext("spring-z-test/src/main/resources/applicationContext.xml");

		ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");

		//ApplicationContext context = new FileSystemXmlApplicationContext("D:\\dev\\Idea\\spring-framework-5.1.8.RELEASE\\spring-z-test\\src\\main\\resources\\applicationContext.xml");

		MyBean myBean = context.getBean("myBean", MyBean.class);
		System.out.println(myBean.getMyName() + "---" + myBean.getMyAge());
		myBean.sayHello("zhangsanfeng");

		MySpringAware mySpringAware = context.getBean("mySpringAware", MySpringAware.class);
		mySpringAware.display();
	}
}