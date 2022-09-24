package com.bjpowernode.mvc.initializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

public interface MyWebApplicationInitializer {

    void onStartup(ServletContext servletContext) throws ServletException;
}