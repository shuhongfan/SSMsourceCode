package com.bjpowernode.ioc.config;

/**
 * 对bean的描述的封装
 */
public class MyGenericBeanDefinition {

    private String beanName;

    private Class beanClass;

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }
}