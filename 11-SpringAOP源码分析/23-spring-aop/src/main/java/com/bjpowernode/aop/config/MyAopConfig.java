package com.bjpowernode.aop.config;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MyAopConfig {

    //map的key= 真实bean对象的方法，比如sendMsg(), value=对切面方法的一个封装 before after around
    private Map<Method, List<MyAspectConfig>> pointAOP = new ConcurrentHashMap<Method, List<MyAspectConfig>>();

    public void putPointAOP(Method method, List<MyAspectConfig> myAspectConfigs) {
        pointAOP.put(method, myAspectConfigs);
    }

    public Map<Method, List<MyAspectConfig>> getPointAOP() {
        return pointAOP;
    }
}
