package com.bjpowernode.ioc.factory;

import com.bjpowernode.ioc.annotation.MyAutowired;
import com.bjpowernode.ioc.config.MyGenericBeanDefinition;
import com.bjpowernode.ioc.config.MyPropertyValue;
import com.bjpowernode.ioc.exception.MyBeanDefinitionException;
import com.bjpowernode.ioc.exception.MyBeansException;
import com.bjpowernode.ioc.util.NameGenerator;
import com.bjpowernode.ioc.util.StringUtils;
import com.bjpowernode.ioc.util.TypeCastUtils;
import com.bjpowernode.mvc.annotation.MyController;
import com.bjpowernode.mvc.annotation.MyRequestMapping;
import com.bjpowernode.mvc.handler.MyHandlerMapping;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class MyDefaultListableBeanFactory implements MyBeanFactory {

    public static final String SET_PREFIX = "set";

    //GenericBeanDefinition的Map
    private final Map<String, MyGenericBeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>(256);

    private final Map<String, Object> singletonObjectsMap = new ConcurrentHashMap<>(256);

    private final List<MyHandlerMapping> myHandlerMappings = new LinkedList<MyHandlerMapping>();

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

                //TODO 判断是不是配置类
                if (beanName.endsWith(".full")) {
                    //反射调用 @MyBean注解的方法

                }

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

                List<MyPropertyValue> myPropertyValueList = myGenericBeanDefinition.getMyPropertyValueList();

                for (MyPropertyValue myPropertyValue : myPropertyValueList) {

                    String name = myPropertyValue.getName();
                    String value = myPropertyValue.getValue();
                    String ref = myPropertyValue.getRef();

                    if (StringUtils.isEmpty(value) && StringUtils.isEmpty(ref)) {
                        throw new MyBeansException("property config of value and ref is null");
                    } else if (StringUtils.isNotEmpty(value) && StringUtils.isNotEmpty(ref)) {
                        throw new MyBeansException("property config of value and ref only configured with one");
                    }

                    Class parmeterClass = beanClass.getDeclaredField(name).getType();
                    //beanClass.getDeclaredMethod("setMyName", String.class);
                    Method writeMethod = beanClass.getDeclaredMethod( SET_PREFIX + NameGenerator.baneUpperCase(name), parmeterClass);
                    writeMethod.setAccessible(true);

                    if (StringUtils.isNotEmpty(value)) {

                        //2、bean对象赋值和依赖注入
                        writeMethod.invoke(beanObject, TypeCastUtils.castType(value, parmeterClass));

                        //相当于依赖注入
                    } else if (StringUtils.isNotEmpty(ref)) {

                        Object object = singletonObjectsMap.get(ref);

                        if (object == null) {
                            //把ref的依赖已经初始化完成了，此时已经有 heBean对象了
                            preInstantiateSingletons(ref);
                        }

                        Object refBeanObject = singletonObjectsMap.get(ref);

                        //给MyBean对象赋heBean属性值
                        writeMethod.invoke(beanObject, refBeanObject);
                    }
                }

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

                //spring mvc @RequestMapping的映射
                if (beanClass.isAnnotationPresent(MyController.class)) {
                    //初始化controller的映射
                    initHandlerMapping(beanClass, beanObject);
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
        } catch (NoSuchFieldException e) {
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
     * 初始化handler的映射
     *
     * @param beanClass
     * @param beanObject
     */
    private void initHandlerMapping(Class beanClass, Object beanObject) {
        Method[] methods = beanClass.getDeclaredMethods();

        for (Method method : methods) {
            if (!method.isAnnotationPresent(MyRequestMapping.class)) {
                continue;
            }
            //方法上出现了@MyRequestMapping这个注解
            MyRequestMapping myRequestMapping = method.getAnnotation(MyRequestMapping.class);
            String url = myRequestMapping.value().trim();

            //需要把映射关系保存起来，以便于后续请求的时候能找到映射关系
            myHandlerMappings.add(new MyHandlerMapping(beanObject, method, url));
        }
    }

    /**
     * 获取handler映射的集合
     *
     * @return
     */
    public List<MyHandlerMapping> getMyHandlerMappings() {
        return myHandlerMappings;
    }
}