package com.bjpowernode.ioc.context;

/**
 * spring 容器（上下文）
 */
public interface MyApplicationContext {

    /**
     * 从spring容器中获取bean对象
     *
     * @param beanName
     * @param beanClassType
     * @param <T>
     * @return
     */
    public <T> T getBean(String beanName, Class<T> beanClassType);

}