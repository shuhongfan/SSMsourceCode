package com.bjpowernode.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * TargetProxy类还不是一个真正的代理类，它是代理类的一部分
 *
 */
public class TargetProxy implements MethodInterceptor {

    /**
     * 获取真正的代理类
     *
     *   //1、jvm内存中生成一个class类；
     *   //2、根据该class类反射创建一个代理对象 $Proxy@564546548
     *   return (T) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
     *                 new Class<?>[] {interfaces},
     *                 this);
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T getProxy(Class<T> clazz) {
        //字节码增强的一个类
        Enhancer enhancer = new Enhancer();

        //设置父类
        enhancer.setSuperclass(clazz);
        //enhancer.setInterfaces(new Class[] {clazz});

        //设置回调类
        enhancer.setCallback(this);

        //创建代理类
        return (T)enhancer.create();
    }

    /**
     * 既可以 sayHello，也可以拦截 sayThanks
     *
     * @param obj
     * @param method
     * @param args
     * @param proxy
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println(method.getName() + "数据缓存start..........");

        //调用目标方法
        Object result = proxy.invokeSuper(obj, args);

        //就像mybatis一样, 需要自己实现接口
        //System.out.println("sayHello...................");

        System.out.println(method.getName() + "数据缓存end..........");

        return result;
    }
}