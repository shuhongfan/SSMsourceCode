package com.bjpowernode.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 实现jdk提供的InvocationHandler接口
 *
 * 实现该接口是为了实现jdk的动态代理
 *
 * 此类不是真正的代理类，真正的代理的类在jvm内存中，我们看不见摸不着的，这个真正的代理类名字一般是以$Proxy.
 *
 */
public class TargetProxy implements InvocationHandler {

    //持有目标接口的引用，动态代理为了适配各种目标类型，把引用使用Object
    private Object target;

    /**
     * 使用构造方法对目标接口的引用实现初始化
     *
     * @param target
     */
    public TargetProxy(Object target) {
        this.target = target;
    }

    /**
     * 获取真正的代理类
     *
     * @param interfaces
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T> T getProxy(Class interfaces) {
        //1、jvm内存中生成一个class类；
        //2、根据该class类反射创建一个代理对象 $Proxy@564546548
        return (T) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                new Class<?>[] {interfaces},
                this);
    }

    /**
     * 覆盖InvocationHandler接口的方法
     * 该方法会对目标接口的方法进行拦截
     *
     * @param proxy 这个就是我们那个代理类，就是jdk生成的那个叫$Proxy.代理类
     * @param method 就是目标接口的方法，比如 sayhi(), work()的反射对象Method;
     * @param args 就是目标接口的方法，比如 sayhi(), work()的参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("前置增强(通知)......");

        //中间是调用目标接口的方法
        Object result = method.invoke(target, args);

        System.out.println("后置增强(通知)......");

        return result;
    }
}