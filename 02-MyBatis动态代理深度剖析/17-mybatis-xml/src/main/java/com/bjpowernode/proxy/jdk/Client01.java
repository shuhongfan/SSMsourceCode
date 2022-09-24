package com.bjpowernode.proxy.jdk;

public class Client01 {

    public static void main(String[] args) {
        //不是用动态代理
        TargetInterface targetClass = new TargetInterfaceImpl();
        targetClass.sayHi();
        targetClass.work();
    }
}