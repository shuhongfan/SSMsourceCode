package com.bjpowernode.aop.aop;

import com.bjpowernode.aop.config.MyAspectConfig;

import java.util.List;
import java.util.Map;

public class MyBeanWapper {

    //原始的那个bean对象
    private Object target;

    //aop表达式的map
    private Map<String, String> pointcutMap;

    //对切面方法before, after......的封装的一个list
    private List<MyAspectConfig> myAspectConfigList;

    public MyBeanWapper(Object target, Map<String, String> pointcutMap, List<MyAspectConfig> myAspectConfigList) {
        this.target = target;
        this.pointcutMap = pointcutMap;
        this.myAspectConfigList = myAspectConfigList;
    }

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    public Map<String, String> getPointcutMap() {
        return pointcutMap;
    }

    public void setPointcutMap(Map<String, String> pointcutMap) {
        this.pointcutMap = pointcutMap;
    }

    public List<MyAspectConfig> getMyAspectConfigList() {
        return myAspectConfigList;
    }

    public void setMyAspectConfigList(List<MyAspectConfig> myAspectConfigList) {
        this.myAspectConfigList = myAspectConfigList;
    }
}