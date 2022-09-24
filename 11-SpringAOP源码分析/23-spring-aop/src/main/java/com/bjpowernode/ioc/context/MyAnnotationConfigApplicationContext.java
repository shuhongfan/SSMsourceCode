package com.bjpowernode.ioc.context;

import com.bjpowernode.ioc.factory.MyAnnotationBeanDefinitionReader;
import com.bjpowernode.ioc.factory.MyBeanFactory;
import com.bjpowernode.ioc.util.MyAssert;

/**
 * 基于注解的Spring容器对象
 *
 */
public class MyAnnotationConfigApplicationContext extends MyGeneralApplicationContext {

    //启动spring容器的锁对象
    private final Object monitor = new Object();

    public MyAnnotationConfigApplicationContext(Class... annotationClass) {
        //1、调用父类的构造方法，创建beanFactory
        super();

        //2、注册配置类（config.java）,也就是注册beanDefinition
        register(annotationClass);

        //3、创建、刷新spring容器
        refresh();
    }

    /**
     * 注册配置类（config.java）,也就是注册bean的定义
     *
     * @param annotationClass
     */
    public void register(Class... annotationClass) {

        //断言判断
        MyAssert.noNullElements(annotationClass, "At least one annotated class must be specified");

        //根据注解类，注册bean定义
        MyAnnotationBeanDefinitionReader reader = new MyAnnotationBeanDefinitionReader(getMyBeanFactory());
        reader.registerBeanDef(annotationClass);
    }

    /**
     * 创建、刷新spring容器
     *
     */
    public void refresh() {
        //线程同步
        synchronized (this.monitor) {
            //获得刷新的bean factory，最终得到是MyDefaultListableBeanFactory
            MyBeanFactory myBeanFactory = getMyBeanFactory();

            //TODO 初始化所有单例bean对象
            finishBeanFactoryInitialization(myBeanFactory);
        }
    }

    /**
     * 初始化所有单例bean对象
     *
     * @param myBeanFactory
     */
    public void finishBeanFactoryInitialization (MyBeanFactory myBeanFactory) {
        //初始化所有单例bean对象
        myBeanFactory.preInstantiateSingletons();
    }
}
