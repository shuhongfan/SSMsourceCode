package com.bjpowernode.ioc.factory;

import com.bjpowernode.aop.aop.MyAspectJAutoProxyCreatorProcessor;
import com.bjpowernode.aop.aop.MyBeanPostProcessor;
import com.bjpowernode.aop.aop.MyBeanWapper;
import com.bjpowernode.aop.config.MyAspectConfig;
import com.bjpowernode.ioc.annotation.MyAutowired;
import com.bjpowernode.ioc.config.MyGenericBeanDefinition;
import com.bjpowernode.ioc.exception.MyBeanDefinitionException;
import com.bjpowernode.ioc.exception.MyBeansException;
import com.bjpowernode.ioc.util.NameGenerator;
import com.bjpowernode.ioc.util.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class MyDefaultListableBeanFactory implements MyBeanFactory {

    public static final String SET_PREFIX = "set";

    //GenericBeanDefinition的Map
    private final Map<String, MyGenericBeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>(256);

    private final Map<String, Object> singletonObjectsMap = new ConcurrentHashMap<>(256);

    private final List<MyBeanPostProcessor> beanPostProcessorList = new LinkedList<MyBeanPostProcessor>();

    //保存切面表达式
    private final Map<String, String> pointcutMap = new ConcurrentHashMap<String, String>();

    //切面方面的集合
    private final List<MyAspectConfig> myAspectConfigList = new LinkedList<MyAspectConfig>();

    /**
     * 注册beanDefinition到BeanFactory中
     *
     * @param beanName
     * @param myGenericBeanDefinition
     */
    public void registerbeanDefinition(String beanName, MyGenericBeanDefinition myGenericBeanDefinition) {
        beanDefinitionMap.put(beanName, myGenericBeanDefinition);
    }

    /**
     * 初始化所有单例bean对象
     *
     */
    public void preInstantiateSingletons() {
        this.preInstantiateSingletons(null);
    }

    /**
     * 初始化所有单例bean对象
     *
     */
    public void preInstantiateSingletons(String refBeanName) throws MyBeanDefinitionException {

        // 实现初始化bean对象，并对bean对象赋值和依赖注入
        Set<String> beanNameSet = beanDefinitionMap.keySet();

        //递归调用该方法，实现依赖注入，在初始化当前bean的时候，需要先初始化另一个bean
        if (StringUtils.isNotEmpty(refBeanName)) {
            beanNameSet = new HashSet<>();
            beanNameSet.add(refBeanName);
        }

        try {
            for (String beanName : beanNameSet) {

                //判断一下，如果该beanName已经创建过对象，则不需要再创建了
                Object singletonObject = singletonObjectsMap.get(beanName);
                if (singletonObject != null) {
                    continue;
                }

                MyGenericBeanDefinition myGenericBeanDefinition = beanDefinitionMap.get(beanName);

                if (myGenericBeanDefinition == null) {
                    throw new MyBeansException("BeanDefinition not init.....");
                }

                Class beanClass = myGenericBeanDefinition.getBeanClass();

                //1、创建bean对象
                Object beanObject = beanClass.getDeclaredConstructor().newInstance();

                //对于扫描的bean对象，需要考虑bean的注入
                Field[] fields = beanClass.getDeclaredFields();
                for (Field field : fields) {

                    if (!field.isAnnotationPresent(MyAutowired.class)) {
                        continue;
                    }

                    MyAutowired myAutowired = field.getDeclaredAnnotation(MyAutowired.class);
                    String autowiredBeanName = myAutowired.value().trim();

                    if ("".equals(autowiredBeanName)) {
                        //如果注入的时候没有指定bean的名字，默认就使用类型名字，首字母小写
                        autowiredBeanName = field.getType().getSimpleName();
                        autowiredBeanName = NameGenerator.nameGenerator(autowiredBeanName);
                    }

                    Object object = singletonObjectsMap.get(autowiredBeanName);

                    if (object == null) {
                        //说明要注入的对象还没有初始化，需要进行初始化，递归
                        preInstantiateSingletons(autowiredBeanName);

                        Object initObject = singletonObjectsMap.get(autowiredBeanName);
                        field.setAccessible(true);
                        field.set(beanObject, initObject);
                    } else {
                        field.setAccessible(true);
                        field.set(beanObject, object);
                    }
                }

                //TODO 如果该bean对象需要aop的拦截，怎么判断需不需要aop拦截呢？
                if (beanPostProcessorList.size() > 0) {
                    //需要aop支持，需要对bean对象增强，那么就需要给bean产生一个代理对象
                    for (MyBeanPostProcessor myBeanPostProcessor : beanPostProcessorList) {

                        MyBeanWapper myBeanWapper = new MyBeanWapper(beanObject, this.pointcutMap, this.myAspectConfigList);

                        //对spring的bean进行增强，生成代理对象
                        beanObject = myBeanPostProcessor.postProcessAfterInitialization(myBeanWapper, beanName);
                    }
                }

                //把初始化好的bean放入到singletonObjectsMap
                singletonObjectsMap.put(beanName, beanObject);
            }
        } catch (NoSuchMethodException e) {
            throw new MyBeanDefinitionException(e);
        } catch (IllegalAccessException e) {
            throw new MyBeanDefinitionException(e);
        } catch (InstantiationException e) {
            throw new MyBeanDefinitionException(e);
        } catch (InvocationTargetException e) {
            throw new MyBeanDefinitionException(e);
        }
    }

    /**
     * 从spring容器中获取bean对象
     *
     * @param beanName
     * @param beanClassType
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T> T getBean(String beanName, Class<T> beanClassType) {
        Object beanObject = singletonObjectsMap.get(beanName);

        //if (beanObject.getClass().isAssignableFrom(beanClassType)) {
            return (T)beanObject;
        /*} else {
            throw new MyBeansException("bean class type cast exception.");
        }*/
    }

    /**
     * 注册 BeanPostProcessor
     *
     * @param beanPostProcessor
     */
    public void registerBeanPostProcessor(MyAspectJAutoProxyCreatorProcessor beanPostProcessor) {
        beanPostProcessorList.add(beanPostProcessor);
    }

    /**
     * 存放aop的表达式
     *
     * @param methodName
     */
    public void putPointcat(String methodName, String exp) {
        pointcutMap.put(methodName, exp);
    }

    /**
     * 存放aop切面方法相关信息
     *
     * @param myAspectConfig
     */
    public void addAspectConfig(MyAspectConfig myAspectConfig) {
        myAspectConfigList.add(myAspectConfig);
    }
}