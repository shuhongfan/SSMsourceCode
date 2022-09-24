package com.bjpowernode.proxy.statics;

public class Test2 {

    public static void main(String[] args) {

        TargetProxy targetProxy = new TargetProxy(new TargetInterfaceImpl());
        targetProxy.sayHello("张无忌");

        System.out.println("---------------------------");

        targetProxy.sayThanks("张三丰");
    }
}