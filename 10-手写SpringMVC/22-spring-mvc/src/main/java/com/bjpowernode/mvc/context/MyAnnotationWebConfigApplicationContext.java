package com.bjpowernode.mvc.context;

import com.bjpowernode.ioc.context.MyAnnotationConfigApplicationContext;

public class MyAnnotationWebConfigApplicationContext extends MyAnnotationConfigApplicationContext {

    public MyAnnotationWebConfigApplicationContext(Class... annotationClass) {
        super(annotationClass);
    }
}