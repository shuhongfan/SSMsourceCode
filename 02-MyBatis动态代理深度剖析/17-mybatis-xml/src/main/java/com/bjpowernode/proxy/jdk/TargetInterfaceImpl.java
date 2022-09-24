package com.bjpowernode.proxy.jdk;

/**
 * 目标接口实现
 *
 */
public class TargetInterfaceImpl implements TargetInterface {

    public void sayHi() {
        System.out.println("Hi, dynamic proxy. sayHi.");
    }

    public void work() {
        System.out.println("Hi, dynamic proxy.work");
    }
}