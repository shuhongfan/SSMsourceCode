package com.bjpowernode.ioc.factory;

import com.bjpowernode.ioc.annotation.MyComponentScan;
import com.bjpowernode.ioc.config.MyGenericBeanDefinition;
import com.bjpowernode.ioc.util.NameGenerator;

public class MyAnnotationBeanDefinitionReader extends MyGeneralBeanDefinitionReader {

    public MyAnnotationBeanDefinitionReader(MyBeanFactory beanFactory) {
        super(beanFactory);
    }

    /**
     * 加载（注册）BeanDefinition --> Map 中
     *
     * @param annotationClass
     */
    public void registerBeanDef(Class... annotationClass) {
        for (Class clazz : annotationClass) {
            registerBeanDefinition(clazz);
        }
    }

    public void registerBeanDefinition (Class annotationClass) {

        //创建一个beanDefinition
        MyGenericBeanDefinition beanDefinition = new MyGenericBeanDefinition();
        beanDefinition.setBeanClass(annotationClass);

        //bean的名称生成
        String beanName = NameGenerator.nameGenerator(annotationClass.getSimpleName());
        beanDefinition.setBeanName(beanName); //config.java

        //把beanDefinition放入beanDefinitionMap的一个Map中，key是beanName，值是beanDefinition对象
        myBeanFactory.registerbeanDefinition(beanName, beanDefinition);

        //TODO 配置类里面的@MyBean注解需要扫描和处理一下


        if (annotationClass.isAnnotationPresent(MyComponentScan.class)) {

            MyComponentScan myComponentScan = (MyComponentScan)annotationClass.getDeclaredAnnotation(MyComponentScan.class);

            //com.bjpowernode.sample
            String basePackage = myComponentScan.basePackages();

            //我们要扫描包，根据包下的类的注解来加载该类的bean的定义
            doScannerPackage(basePackage);
        }
    }
}
