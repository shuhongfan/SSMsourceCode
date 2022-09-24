package com.bjpowernode;

import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

import java.io.File;

/**
 * 通过tomcat启动Spring MVC程序
 *
 */
public class Test {

    /**
     * 项目启动的入口main方法
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        start();
    }

    /**
     * 启动tomcat
     *
     * @throws Exception
     */
    public static void start() throws Exception {

        // 创建内嵌的Tomcat
        Tomcat tomcatServer = new Tomcat();

        // 设置Tomcat端口
        tomcatServer.setPort(8080);

        // 读取项目路径，加载项目资源
        StandardContext ctx = (StandardContext) tomcatServer.addWebapp("/22-spring-mvc",
                new File("22-spring-mvc/src/main/webapp").getAbsolutePath());

        // 不重新部署加载资源
        ctx.setReloadable(false);

        // 创建WebRoot
        WebResourceRoot resources = new StandardRoot(ctx);

        // 指定编译后的class文件位置
        File additionWebInfClasses = new File("target/classes");

        // 添加web资源
        resources.addPreResources(new DirResourceSet(resources, "/",
                additionWebInfClasses.getAbsolutePath(), "/"));

        // 启动内嵌的Tomcat
        tomcatServer.start();

        // 等待前端请求，不退出程序
        tomcatServer.getServer().await();
    }
}
