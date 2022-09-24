package com.bjpowernode.factorybean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

    public static void main(String[] args) {

        //启动spring ioc容器
        ApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:applicationContext.xml");

        for (int i=0; i<5; i++) {
            Object configBean = context.getBean("myFactoryBean");
            System.out.println(">>>>>>" + configBean);
        }

        Object myFactoryBean = context.getBean("&myFactoryBean");
        System.out.println(myFactoryBean);
    }
}