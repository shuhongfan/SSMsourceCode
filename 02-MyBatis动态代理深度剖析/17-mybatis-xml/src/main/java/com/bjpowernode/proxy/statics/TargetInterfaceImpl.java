package com.bjpowernode.proxy.statics;

/**
 * 目标接口的实现
 *
 */
public class TargetInterfaceImpl implements TargetInterface {

    @Override
    public void sayHello(String name) {
        System.out.println("sayHello, " + name);
    }

    @Override
    public void sayThanks(String name) {
        System.out.println("sayThanks, " + name);
    }
}
