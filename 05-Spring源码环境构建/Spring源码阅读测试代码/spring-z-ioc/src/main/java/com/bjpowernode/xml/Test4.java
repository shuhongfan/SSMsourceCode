package com.bjpowernode.xml;

import com.bjpowernode.bean.MyBean;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * http://www.bjpowernode.com
 *
 * @author 北京动力节点
 */
public class Test4 {

	public static void main(String[] args) throws InterruptedException {
		ApplicationContext context =
				new ClassPathXmlApplicationContext("applicationContext.xml");

		//DefaultListableBeanFactory也是spring的一个ioc容器，功能比ClassPathXmlApplicationContext这个ioc容器少一点
		DefaultListableBeanFactory factory = new DefaultListableBeanFactory();

		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
		Resource resource = new ClassPathResource("applicationContext.xml");
		//加载bean定义
		reader.loadBeanDefinitions(resource);

		MyBean myBean = factory.getBean("myBean", MyBean.class);
		myBean.sayHello("zhangsanfeng");
	}
}