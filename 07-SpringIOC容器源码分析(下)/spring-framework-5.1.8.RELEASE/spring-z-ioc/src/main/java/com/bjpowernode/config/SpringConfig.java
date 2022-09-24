package com.bjpowernode.config;

import com.bjpowernode.bean.HeBean;
import com.bjpowernode.bean.MyBean;
import org.springframework.context.annotation.*;

@Configuration // == xml文件  springConfig
public class SpringConfig {

	/**
	 * <bean id="myBean" class="com.bjpowernode.bean.MyBean">
	 *
	 * </bean>
	 * @return
	 */
	@Bean
	public MyBean myBean() {
		return new MyBean();
	}

	/**
	 * <bean id="heBean" class="com.bjpowernode.bean.HeBean">
	 *
	 * </bean>
	 * @return
	 */
	@Bean
	public HeBean heBean() {
		return new HeBean();
	}
}