package com.bjpowernode.spring.factory;

import com.bjpowernode.spring.annotation.MyComponent;
import com.bjpowernode.spring.annotation.MyRepository;
import com.bjpowernode.spring.annotation.MyService;
import com.bjpowernode.spring.config.MyGenericBeanDefinition;
import com.bjpowernode.spring.exception.MyBeanDefinitionException;
import com.bjpowernode.spring.util.NameGenerator;

import java.io.File;

public class MyGeneralBeanDefinitionReader {

    //持有BeanFactory的引用
    protected MyBeanFactory myBeanFactory;

    public MyGeneralBeanDefinitionReader(MyBeanFactory myBeanFactory) {
        this.myBeanFactory = myBeanFactory;
    }

    /**
     * 对包进行扫描
     *
     * @param basePackage
     */
    protected void doScannerPackage (String basePackage) {

        //com.bjpowernode.bean
        // D:/dev/idea/xxxx/com/bjpowernode/bean
        String filePath = Thread.currentThread().getContextClassLoader()
                .getResource(basePackage.replaceAll("\\.", "/")).getFile();

        File dir = new File(filePath);
        try {
            for (File file : dir.listFiles()) {
                if (file.isDirectory()) { //D:/dev/idea/xxxx/com/bjpowernode/bean/mis
                    //还是目录，递归调用一下
                    doScannerPackage(basePackage + "." + file.getName());
                } else {
                    //已经是文件了，.class文件
                    if (!file.getName().contains(".class")) {
                        continue;
                    }
                    //com.bjpowernode.bean.MyBean
                    String className = basePackage + "." + file.getName().replace(".class", "");

                    Class clazz = Class.forName(className);

                    //只有当这个三个注解出现了，我们才需要加载他们的类，没有加注解的，我们不需要加载他们
                    if (clazz.isAnnotationPresent(MyService.class)
                            || clazz.isAnnotationPresent(MyRepository.class)
                            || clazz.isAnnotationPresent(MyComponent.class)) {

                        //底层创建了一个GenericBeanDefinition
                        MyGenericBeanDefinition myGenericBeanDefinition = new MyGenericBeanDefinition();
                        //设置bean定义对象的beanClass类
                        myGenericBeanDefinition.setBeanClass(clazz);

                        //计算一些bean的名称
                        String beanName = null;
                        if (clazz.getInterfaces().length > 0) {
                            //对于实现了接口的情况
                            for (Class inf : clazz.getInterfaces()) {
                                //以接口名作为bean的名字
                                beanName = NameGenerator.nameGenerator(inf.getSimpleName());

                                //设置beanName
                                myGenericBeanDefinition.setBeanName(beanName);

                                //把beanDefinition放入beanDefinitionMap的一个Map中，key是beanName，值是beanDefinition对象
                                this.myBeanFactory.registerbeanDefinition(beanName, myGenericBeanDefinition);
                            }
                        }
                        //设置beanName
                        beanName = NameGenerator.nameGenerator(clazz.getSimpleName());
                        myGenericBeanDefinition.setBeanName(beanName);
                        //把beanDefinition放入beanDefinitionMap的一个Map中，key是beanName，值是beanDefinition对象
                        this.myBeanFactory.registerbeanDefinition(beanName, myGenericBeanDefinition);
                    }
                }
            }
        } catch (Exception e) {
            throw new MyBeanDefinitionException(e);
        }
    }
}