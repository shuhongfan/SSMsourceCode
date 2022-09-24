package com.bjpowernode.proxy.statics;

/**
 * 目标接口的代理类
 *
 */
public class TargetProxy implements TargetInterface {

    //持有目标接口的引用
    private TargetInterface targetInterface;

    public TargetProxy(TargetInterface targetInterface) {
        this.targetInterface = targetInterface;
    }

    @Override
    public void sayHello(String name) {
        System.out.println("start..............");

        //中间调目标接口的真正的实现
        targetInterface.sayHello(name);

        System.out.println("end..............");
    }

    @Override
    public void sayThanks(String name) {
        System.out.println("start..............");

        //中间调目标接口的真正的实现
        targetInterface.sayThanks(name);

        System.out.println("end..............");
    }
}
