package com.bjpowernode.servlet;

import com.bjpowernode.config.RootConfig;
import com.bjpowernode.config.WebServletConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * DispatcherServlet 初始化
 *
 * TODO 我们写的类
 *
 * @HandlesTypes(WebApplicationInitializer.class) 扩展
 */
public class DispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	/**
	 * 告诉spring，根的配置是哪个？
	 *
	 * @return
	 */
    @Override
    protected Class<?>[] getRootConfigClasses() {
    	//返回null表示不配置spring ioc的父容器
        return new Class[] {RootConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {WebServletConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }
}