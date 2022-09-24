package com.bjpowernode;

import com.bjpowernode.service.GoodsService;
import com.bjpowernode.service.MyGoodsService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

	public static void main(String[] args) throws Exception {

		//设置输出cglib动态代理产生的类
		//System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "D:\\data");

		//IOC、AOP(事务)、MVC
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");

		//此处Spring事务底层使用 JDK动态代理
		GoodsService goodsService = context.getBean(GoodsService.class);

		//此处Spring事务底层使用 Cglib动态代理
		MyGoodsService myGoodsService = context.getBean(MyGoodsService.class);


		//执行业务代码
		int update = myGoodsService.a (1);
		System.out.println("更新结果：" + update);
	}
}