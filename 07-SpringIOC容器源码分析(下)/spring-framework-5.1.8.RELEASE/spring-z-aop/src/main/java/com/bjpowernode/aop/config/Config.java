package com.bjpowernode.aop.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy //开启aop功能
@ComponentScan(basePackages = "com.bjpowernode.aop")
public class Config {

}