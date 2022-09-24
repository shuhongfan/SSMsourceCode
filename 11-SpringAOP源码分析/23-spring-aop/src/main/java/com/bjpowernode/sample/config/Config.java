package com.bjpowernode.sample.config;

import com.bjpowernode.aop.annotation.MyEnableAspectJAutoProxy;
import com.bjpowernode.ioc.annotation.MyComponentScan;

@MyEnableAspectJAutoProxy //开启aop功能
@MyComponentScan(basePackages = "com.bjpowernode.sample")
public class Config {

}