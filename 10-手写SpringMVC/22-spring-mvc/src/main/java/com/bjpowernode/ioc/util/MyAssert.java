package com.bjpowernode.ioc.util;

/**
 * 定义一个断言类
 * 一些非法数据验证的工具
 */
public class MyAssert {

    public static void noNullElements(Object[] array, String message) {
        if (array != null) {
            for (Object element : array) {
                if (element == null) {
                    throw new IllegalArgumentException(message);
                }
            }
        }
    }

    public static void notNull(Object object, String message) {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }
}