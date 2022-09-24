package com.bjpowernode.sample.bean;

import com.bjpowernode.ioc.annotation.MyComponent;

@MyComponent
public class MyBeanImpl implements MyBean {

    public void sayHi() {
        System.out.println("业务方法执行， sayHi");
    }
}