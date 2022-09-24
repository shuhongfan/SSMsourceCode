package com.bjpowernode.ioc.factory;

import com.bjpowernode.aop.aop.MyAspectJAutoProxyCreatorProcessor;
import com.bjpowernode.aop.config.MyAspectConfig;
import com.bjpowernode.ioc.config.MyGenericBeanDefinition;

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
     * 注册 BeanPostProcessor
     *
     * @param beanPostProcessor
     */
    public void registerBeanPostProcessor(MyAspectJAutoProxyCreatorProcessor beanPostProcessor);

    /**
     * 存放aop的表达式
     *
     * @param methodName
     */
    public void putPointcat(String methodName, String exp);

    /**
     * 存放aop切面方法相关信息
     *
     * @param myAspectConfig
     */
    public void addAspectConfig(MyAspectConfig myAspectConfig);

}