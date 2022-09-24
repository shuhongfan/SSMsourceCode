package com.bjpowernode.xml;

import com.bjpowernode.bean.MyBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * http://www.bjpowernode.com
 *
 * @author 北京动力节点
 */
public class Test1 {

	public static void main(String[] args) throws InterruptedException {

		//如果是注解开发
		AnnotationConfigApplicationContext annotationConfigApplicationContext;

		//Spring IOC容器
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

		MyBean myBean = context.getBean("myBean", MyBean.class);
		System.out.println(myBean.getMyName() + "---" + myBean.getMyAge());
		myBean.sayHello("zhangsanfeng");

		//MySpringAware mySpringAware = context.getBean("mySpringAware", MySpringAware.class);
		//mySpringAware.display();

		//HeBean heBean = context.getBean("heBean", HeBean.class);
		//System.out.println(heBean.getHeName() + "我的bean---" + heBean.getHeAge());

		//MyInitializingBean myInitializingBean = context.getBean("myInitializingBean", MyInitializingBean.class);
		//System.out.println(myInitializingBean.getName());
	}
}