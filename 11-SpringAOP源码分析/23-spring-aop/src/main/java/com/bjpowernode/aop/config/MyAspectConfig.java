package com.bjpowernode.aop.config;

import java.lang.reflect.Method;

/**
 * 对切面方法的一个封装 before after around
 */
public class MyAspectConfig {

    //切面类本身的对象，也就是我们的 SmsAspect 类的对象
    private Object aspect;

    //切面类的before、after、around等方法的发射对象
    private Method point;

    //map里面切面表达式的key
    private String expressionKey;

    public MyAspectConfig(Object aspect, Method point, String expressionKey) {
        this.aspect = aspect; //aspect类的对象
        this.point = point; //切入的方法
        this.expressionKey = expressionKey; //表达式键
    }

    public Object getAspect() {
        return aspect;
    }

    public void setAspect(Object aspect) {
        this.aspect = aspect;
    }

    public Method getPoint() {
        return point;
    }

    public void setPoint(Method point) {
        this.point = point;
    }

    public String getExpressionKey() {
        return expressionKey;
    }

    public void setExpressionKey(String expressionKey) {
        this.expressionKey = expressionKey;
    }
}
