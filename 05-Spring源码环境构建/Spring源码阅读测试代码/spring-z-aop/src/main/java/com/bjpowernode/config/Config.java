package com.bjpowernode.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy //开启aop功能

@ComponentScan(basePackages = "com.bjpowernode")
@Configuration //表示是一个配置类，不加也可以
public class Config {

}