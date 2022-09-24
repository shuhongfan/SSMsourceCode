package com.bjpowernode.ioc.factory;

import com.bjpowernode.ioc.config.MyGenericBeanDefinition;
import com.bjpowernode.mvc.handler.MyHandlerMapping;

import java.util.List;

public interface MyBeanFactory {

    /**
     * 注册beanDefinition到BeanFactory中
     *
     * @param beanName
     * @param myGenericBeanDefinition
     */
    public void registerbeanDefinition(String beanName, MyGenericBeanDefinition myGenericBeanDefinition);

    /**
     * 初始化所有单例bean对象
     *
     */
    public void preInstantiateSingletons();

    /**
     * 初始化所有单例bean对象
     *
     */
    public void preInstantiateSingletons(String beanName);

    /**
     * 从spring容器中获取bean对象
     *
     * @param beanName
     * @param beanClassType
     * @param <T>
     * @return
     */
    public <T> T getBean(String beanName, Class<T> beanClassType);

    /**
     * 获取handler映射的集合
     *
     * @return
     */
    public List<MyHandlerMapping> getMyHandlerMappings();
}