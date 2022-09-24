package com.bjpowernode.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan(basePackages = {"com.bjpowernode.bean"})
//@Component
@Configuration // == xml文件  springConfig
public class SpringConfig {

	public SpringConfig() {
	}

	/**
	 * <bean id="myBean" class="com.bjpowernode.bean.MyBean">
	 *
	 * </bean>
	 * @return
	 */
	/*@Bean
	public MyBean myBean() {
		return new MyBean();
	}*/

	/**
	 * <bean id="heBean" class="com.bjpowernode.bean.HeBean">
	 *
	 * </bean>
	 * @return
	 */
	/*@Bean
	public HeBean heBean() {
		return new HeBean();
	}*/
}