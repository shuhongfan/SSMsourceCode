package com.bjpowernode.spring.context;

import com.bjpowernode.spring.factory.MyBeanFactory;
import com.bjpowernode.spring.factory.MyDefaultListableBeanFactory;

public class MyGeneralApplicationContext implements MyApplicationContext {

    //持有MyBeanFactory的引用
    private MyBeanFactory myBeanFactory;

    public MyGeneralApplicationContext() {
        //创建beanfactory
        this.myBeanFactory = new MyDefaultListableBeanFactory();
    }

    /**
     * 从spring容器中获取bean对象
     *
     * @param beanName
     * @param beanClassType
     * @param <T>
     * @return
     */
    public <T> T getBean(String beanName, Class<T> beanClassType) {
        return this.myBeanFactory.getBean(beanName, beanClassType);
    }

    /**
     * 获取beanFactory
     *
     * @return
     */
    public MyBeanFactory getMyBeanFactory() {
        return this.myBeanFactory;
    }
}