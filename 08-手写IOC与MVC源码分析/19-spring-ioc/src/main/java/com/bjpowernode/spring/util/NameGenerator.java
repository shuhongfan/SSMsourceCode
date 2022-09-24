package com.bjpowernode.spring.util;

/**
 * beanName 生成器
 */
public class NameGenerator {

    /**
     * 首字母小写
     *
     * @param name
     * @return
     */
    public static String nameGenerator(String name) {
        return name.substring(0,1).toLowerCase() + name.substring(1);
    }

    /**
     * 首字母大写
     *
     * @param name
     * @return
     */
    public static String baneUpperCase(String name) {
        return name.substring(0,1).toUpperCase() + name.substring(1);
    }
}
