package com.bjpowernode.aop.aop;

import com.bjpowernode.ioc.exception.MyBeansException;
import com.bjpowernode.aop.config.MyAopConfig;
import com.bjpowernode.aop.config.MyAspectConfig;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyAspectJAutoProxyCreatorProcessor implements MyBeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws MyBeansException {
        return null;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws MyBeansException {

        MyBeanWapper myBeanWapper = (MyBeanWapper)bean;

        //原始的spring的bean对象  smsServiceImpl
        Object target = myBeanWapper.getTarget();
        //aop切面表达式
        Map<String, String> pointcutMap = myBeanWapper.getPointcutMap();
        //切面方法，before、after....
        List<MyAspectConfig> myAspectConfigList = myBeanWapper.getMyAspectConfigList();

        MyAopConfig myAopConfig = new MyAopConfig();

        //sendMsg() ......
        for (Method method : target.getClass().getDeclaredMethods()) {

            List<MyAspectConfig> myAspectConfigs = new ArrayList<MyAspectConfig>();

            // method --> smsServiceImpl类的sendSms() 方法
            for (MyAspectConfig myAspectConfig : myAspectConfigList) {

                // aop 表达式 execution(public * com.bjpowernode.sample.service..*.*(..))
                String expression = pointcutMap.get(myAspectConfig.getExpressionKey());

                //正则编译一下aop表达式
                Pattern pattern = Pattern.compile(expression);
                // public void com.bjpowernode.sample.service.impl.SmsServiceImpl.sendSms()
                Matcher matcher = pattern.matcher(method.toString());

                if (matcher.matches()) {
                    //能满足切面表达式，需要进行切面增强
                    myAspectConfigs.add(myAspectConfig);
                }
            }

            if (myAspectConfigs.size() > 0) {
                myAopConfig.putPointAOP(method, myAspectConfigs);
            }
        }

        if (myAopConfig.getPointAOP().size() > 0) {
            //说明当前的bean对象生成代理对象 jdk动态代理 ,
            // SmsServiceImpl.sendMsg() -> 首先调动态代理的 InvocationHandler 里面的invoke()方法
            return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                    target.getClass().getInterfaces(),
                    new MyInvocationHandler(target, myAopConfig));
        }
        //不需要产生代理
        return target;
    }
}
