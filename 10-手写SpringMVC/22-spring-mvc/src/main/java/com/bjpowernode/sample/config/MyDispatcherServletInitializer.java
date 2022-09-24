package com.bjpowernode.sample.config;

import com.bjpowernode.mvc.initializer.support.MyDispatcherServletWebApplicationInitializer;

/**
 * DispatcherServlet 初始化
 *
 * TODO 我们写的类
 *
 * @HandlesTypes(MyWebApplicationInitializer.class) 扩展
 */
public class MyDispatcherServletInitializer extends MyDispatcherServletWebApplicationInitializer {

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {WebServletConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }
}