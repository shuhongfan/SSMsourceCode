package com.bjpowernode.aop.aop;

import java.lang.reflect.Method;

public class MyProceedingJoinPoint {

    //smsServiceImpl
    private Object target;
    //smsServiceImpl.sendMsg()方法的反射对象Method
    private Method method;
    //smsServiceImpl.sendMsg()方法的参数
    private Object[] args;

    public MyProceedingJoinPoint(Object target, Method method, Object[] args) {
        this.target = target;
        this.method = method;
        this.args = args;
    }

    /**
     * 调用目标方法
     *
     * @return
     * @throws Throwable
     */
    public Object proceed() throws Throwable {
        return method.invoke(target, args);
    }

    public Object getTarget() {
        return target;
    }

    public Method getMethod() {
        return method;
    }

    public Object[] getArgs() {
        return args;
    }
}