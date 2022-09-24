package com.bjpowernode.mvc.initializer.support;

import com.bjpowernode.ioc.util.MyAssert;
import com.bjpowernode.mvc.initializer.MyWebApplicationInitializer;
import com.bjpowernode.mvc.servlet.MyDispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public abstract class MyDispatcherServletWebApplicationInitializer implements MyWebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        //TODO 注册DispatcherServlet
        registerDispatcherServlet(servletContext);
    }

    /**
     * 注册DispatcherServlet
     *
     * @param servletContext
     */
    protected void registerDispatcherServlet(ServletContext servletContext) {
        //获取servlet的配置类
        Class<?>[] annotatedClasses = getServletConfigClasses();

        MyAssert.noNullElements(annotatedClasses, "createServletApplicationContext() must not return null");
        //TODO 创建dispatcherServlet
        MyDispatcherServlet myDispatcherServlet = new MyDispatcherServlet(annotatedClasses);

        MyAssert.notNull(myDispatcherServlet, "createDispatcherServlet(annotatedClasses) must not return null");
        //TODO 把创建的DispatcherServlet加入到servletContext上下文中
        ServletRegistration.Dynamic registration = servletContext.addServlet(MyDispatcherServlet.DEFAULT_SERVLET_NAME, myDispatcherServlet);
        if (registration == null) {
            throw new IllegalStateException("Failed to register servlet with name '" +
                    MyDispatcherServlet.DEFAULT_SERVLET_NAME + "'. " +
                    "Check if there is another servlet registered under the same name.");
        }
        //设置优先启动 1
        registration.setLoadOnStartup(1);
        //设置dispatcherServlet的映射
        registration.addMapping(getServletMappings());
    }

    protected abstract Class<?>[] getServletConfigClasses();

    protected abstract String[] getServletMappings();

}