package com.bjpowernode.proxy.cglib;

import net.sf.cglib.core.DebuggingClassWriter;

public class Test {

    public static void main(String[] args) {

        //通过参数设置，把动态代理生成的class文件输出到磁盘上，默认是不会输出到磁盘的，所以动态代理生成的类我们是看不见摸不着的
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "D:/code");

        TargetProxy targetProxy = new TargetProxy();

        /*
        //拿到目标类的代理类,产生一个 TargetInterface$$EnhancerByCGLIB$$a2c85c33 对象
        TargetInterface targetInterface = targetProxy.getProxy(TargetInterface.class);

        targetInterface.sayHello("张无忌");

        System.out.println("-------------------");

        targetInterface.sayThanks("张无忌");
        */

        Student student = targetProxy.getProxy(Student.class);
        student.sleeping();
    }
}
