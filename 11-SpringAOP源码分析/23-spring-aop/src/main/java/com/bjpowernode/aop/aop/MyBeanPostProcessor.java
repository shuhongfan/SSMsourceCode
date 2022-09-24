package com.bjpowernode.aop.aop;

import com.bjpowernode.ioc.exception.MyBeansException;

public interface MyBeanPostProcessor {

    default Object postProcessBeforeInitialization(Object bean, String beanName) throws MyBeansException {
        return bean;
    }

    default Object postProcessAfterInitialization(Object bean, String beanName) throws MyBeansException {
        return bean;
    }
}