package com.bjpowernode.aop.aop;

import com.bjpowernode.aop.annotation.MyAfter;
import com.bjpowernode.aop.annotation.MyAround;
import com.bjpowernode.aop.annotation.MyBefore;
import com.bjpowernode.aop.config.MyAopConfig;
import com.bjpowernode.aop.config.MyAspectConfig;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

public class MyInvocationHandler implements InvocationHandler {

    //原始的bean对象，也就是 smsServiceImpl
    private Object target;

    //封装的是一个Map smsServiceImpl.sendMsg()方法的反射对象Method是key，
    //value是sendMsg()方法签名 符合aop表达式的增强方法(before,after,around...)的List
    private MyAopConfig myAopConfig;

    public MyInvocationHandler(Object target, MyAopConfig myAopConfig) {
        this.target = target;
        this.myAopConfig = myAopConfig;
    }

    /**
     * 动态代理的拦截方法，当目标方法sendMsg()执行时候，会先执行该方法invoke(....)
     *
     * @param proxy  --jdk给我们生成的动态代理对象，$Proxy0@48945
     * @param method --当前要执行的目标方法，也就是sendMsg()方法的反射Method对象
     * @param args  --当前要执行的目标方法，也就是sendMsg()方法的参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        //得到方法所在的类如果是Object，直接调用，不做拦截处理
        if (Object.class.equals(method.getDeclaringClass())) {
            return method.invoke(this, args);
        }

        //target == smsServiceImpl , myMethod ->sendMsg()
        Method myMethod = this.target.getClass().getDeclaredMethod(method.getName(), method.getParameterTypes());

        //拿到myMethod=sendMsg()方法需要进行增强的方法列表（比如需要after、before）
        List<MyAspectConfig> myAspectConfigList = myAopConfig.getPointAOP().get(myMethod);

        Object result = null;

        boolean isBefore = false;
        for (MyAspectConfig myAspectConfig : myAspectConfigList) {
            if (myAspectConfig.getPoint().isAnnotationPresent(MyBefore.class)) {
                isBefore = true;
                //调用前置增强方法
                myAspectConfig.getPoint().invoke(myAspectConfig.getAspect());
            }
        }

        //是否出现环绕增强
        boolean isAround = false;

        for (MyAspectConfig myAspectConfig : myAspectConfigList) {
            if (myAspectConfig.getPoint().isAnnotationPresent(MyAround.class)) {
                MyProceedingJoinPoint joinPoint = new MyProceedingJoinPoint(this.target, method, args);
                //myAspectConfig.getPoint() == public Object around(...)方法的反射Method对象
                result = myAspectConfig.getPoint().invoke(myAspectConfig.getAspect(), joinPoint);
                isAround = true;
            }
        }

        boolean isAfter = false;
        for (MyAspectConfig myAspectConfig : myAspectConfigList) {
            if (myAspectConfig.getPoint().isAnnotationPresent(MyAfter.class)) {
                isAfter = true;
                if (isAround) {
                    //调用后置增强方法
                    myAspectConfig.getPoint().invoke(myAspectConfig.getAspect());
                } else {
                    //先调用一下目标方法 smsServiceImpl.sendMsg()
                    result = myMethod.invoke(this.target, args);

                    //调用后置增强方法
                    myAspectConfig.getPoint().invoke(myAspectConfig.getAspect());
                }
            }
        }

        if (isAfter) {
            //先调用一下目标方法 smsServiceImpl.sendMsg()
            result = myMethod.invoke(this.target, args);
        }
        return result;
    }
}