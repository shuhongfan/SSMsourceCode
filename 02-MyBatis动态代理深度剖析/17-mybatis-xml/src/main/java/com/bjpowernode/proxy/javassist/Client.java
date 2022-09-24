package com.bjpowernode.proxy.javassist;

public class Client {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {

        TargetProxy proxy = new TargetProxy();

        //代理得到的是类似于 TargetInterfaceImpl_$$_jvst464_0 的对象
        TargetInterfaceImpl targetClass = (TargetInterfaceImpl)proxy.getProxy(TargetInterfaceImpl.class);
        targetClass.sayHello("张无忌");
    }
}