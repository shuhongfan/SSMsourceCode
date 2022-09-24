package com.bjpowernode.proxy.javassist;

/**
 * 目标类的实现
 *
 */
public class TargetInterfaceImpl implements TargetInterface {

    @Override
    public void sayHello(String name) {
        System.out.println("TargetClassImpl sayHello..........");
    }

    @Override
    public void sayThanks() {
        System.out.println("TargetClassImpl sayThanks..........");
    }
}