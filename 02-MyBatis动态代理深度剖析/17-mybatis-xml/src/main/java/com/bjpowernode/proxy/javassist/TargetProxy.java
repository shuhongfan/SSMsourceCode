package com.bjpowernode.proxy.javassist;

import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.ProxyFactory;
import javassist.util.proxy.ProxyObject;

import java.lang.reflect.Method;

/**
 * 不是真正的代理类，是生成代理类的其中一个环节
 *
 */
public class TargetProxy implements MethodHandler {

    /**
     * 获取代理对象
     *
     * @param clazz
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public Object getProxy(Class<?> clazz) throws InstantiationException, IllegalAccessException {
        // 代理工厂
        ProxyFactory proxyFactory = new ProxyFactory();

        // 设置需要创建子类的父类
        proxyFactory.setSuperclass(clazz);

        // 通过字节码技术动态创建子类实例
        proxyFactory.writeDirectory = "D:/aaa";
        Object proxy = proxyFactory.createClass().newInstance();

         //在调用目标方法时，Javassist会回调MethodHandler接口方法拦截，
         //来实现你自己的代理逻辑，类似于JDK中的InvocationHandler接口
        ((ProxyObject)proxy).setHandler(this);

        // 返回代理类对象
        return proxy;
    }

    @Override
    public Object invoke(Object self, Method thisMethod, Method proceed, Object[] args) throws Throwable {

        System.out.println("开启事务-------");

        //调用目标类的方法
        Object result = proceed.invoke(self, args);

        System.out.println("提交事务-------");

        return result;
    }
}