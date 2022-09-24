package com.bjpowernode.proxy.jdk;

public class Client02 {

    public static void main(String[] args) {
        //使用动态代理
        //保存生成的代理类文件
        System.setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        //获取代理类（还不是真正的代理对象）
        TargetProxy proxyClass = new TargetProxy(new TargetInterfaceImpl());

        //真正的代理对象 $Proxy0对象
        TargetInterface targetClass = (TargetInterface)proxyClass.getProxy(TargetInterface.class);

        targetClass.sayHi(); // $Proxy0 的 super.h.invoke(this, m3, (Object[])null);

        System.out.println("--------------------");

        targetClass.work();
    }
}
