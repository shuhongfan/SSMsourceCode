package com.bjpowernode.factorybean;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

@Component //我的工厂bean是spring ioc容器的bean对象
public class MyService implements FactoryBean<MyBean> {

	//一个类实现了 FactoryBean 接口，那么当我们从spring ioc容器中获取该类的对象时，
	// spring容器会调用 getObject() 方法，把方法的返回结果给我们

    @Override
    public MyBean getObject() throws Exception {
        System.out.println("调用MyFactoryBean-->getObject()方法");
		MyBean myBean = new MyBean();
        return myBean;
    }

    @Override
    public Class<?> getObjectType() {
        return MyBean.class;
    }
}