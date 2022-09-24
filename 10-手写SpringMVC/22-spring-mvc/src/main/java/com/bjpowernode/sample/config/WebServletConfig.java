package com.bjpowernode.sample.config;

import com.bjpowernode.ioc.annotation.MyBean;
import com.bjpowernode.ioc.annotation.MyComponentScan;
import com.bjpowernode.mvc.view.MyInternalResourceViewResolver;

@MyComponentScan(basePackages = "com.bjpowernode.sample")
public class WebServletConfig {

    /**
     *
     * @return
     */
    @MyBean
    public MyInternalResourceViewResolver viewResolver() {
        MyInternalResourceViewResolver viewResolver = new MyInternalResourceViewResolver();
        viewResolver.setPrefix("/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
}