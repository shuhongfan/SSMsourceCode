package com.bjpowernode.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * 对所有的BeanDefinition有效
 *
 * ApplicationContext --> BeanFactory  (BeanFactoryPostProcessor)
 */
@Component //把BeanFactoryPostProcessor加入到spring ioc容器
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		System.out.println("调用了BeanFactoryPostProcessor......");

		String[] beanStr = beanFactory.getBeanDefinitionNames();

		for (String beanName : beanStr) {
			if ("MyBean".equalsIgnoreCase(beanName)) {
				BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);
				//我人为修改了 BeanDefinition
				beanDefinition.getPropertyValues().add("myName", "张无忌");
				beanDefinition.setLazyInit(true);
			}
		}
	}
}