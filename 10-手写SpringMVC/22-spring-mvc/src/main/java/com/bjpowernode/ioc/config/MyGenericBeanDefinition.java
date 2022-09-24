package com.bjpowernode.ioc.config;

import java.util.ArrayList;
import java.util.List;

/**
 * 对bean的描述的封装
 */
public class MyGenericBeanDefinition {

    private String beanName;

    private Class beanClass;

    private List<MyPropertyValue> myPropertyValueList = new ArrayList<MyPropertyValue>();

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

    public List<MyPropertyValue> getMyPropertyValueList() {
        return myPropertyValueList;
    }

    public void setMyPropertyValueList(List<MyPropertyValue> myPropertyValueList) {
        this.myPropertyValueList = myPropertyValueList;
    }
}