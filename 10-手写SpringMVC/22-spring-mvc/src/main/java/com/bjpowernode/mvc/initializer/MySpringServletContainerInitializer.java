package com.bjpowernode.mvc.initializer;

import com.bjpowernode.ioc.util.MyReflectionUtils;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.HandlesTypes;
import java.lang.reflect.Modifier;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@HandlesTypes(value = MyWebApplicationInitializer.class)
public class MySpringServletContainerInitializer implements ServletContainerInitializer {

    @Override
    public void onStartup(Set<Class<?>> myWebAppInitializerClasses, ServletContext servletContext) throws ServletException {
        //初始化的WebApplicationInitializer集合
        List<MyWebApplicationInitializer> initializers = new LinkedList<>();

        if (myWebAppInitializerClasses != null) {
            for (Class<?> waiClass : myWebAppInitializerClasses) {
                if (!waiClass.isInterface() && !Modifier.isAbstract(waiClass.getModifiers()) &&
                        MyWebApplicationInitializer.class.isAssignableFrom(waiClass)) {
                    //isAssignableFrom(..) 当前类是否与参数里面的类相同，或者是参数里面的类的超类或接口
                    try {
                        //反射创建MyWebApplicationInitializer对象
                        initializers.add((MyWebApplicationInitializer)
                                MyReflectionUtils.accessibleConstructor(waiClass).newInstance());
                    } catch (Throwable ex) {
                        throw new ServletException("Failed to instantiate WebApplicationInitializer class", ex);
                    }
                }
            }
        }

        if (initializers.isEmpty()) {
            servletContext.log("No Spring WebApplicationInitializer types detected on classpath");
            return;
        }

        servletContext.log(initializers.size() + " Spring WebApplicationInitializers detected on classpath");
        for (MyWebApplicationInitializer initializer : initializers) {
            //@HandlesTypes(WebApplicationInitializer.class)
            //TODO 循环每一个实现WebApplicationInitializer接口的实现类，调用其onStartup(..)方法
            initializer.onStartup(servletContext);
        }
    }
}
