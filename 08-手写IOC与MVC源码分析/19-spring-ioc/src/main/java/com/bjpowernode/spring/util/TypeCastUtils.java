package com.bjpowernode.spring.util;

public class TypeCastUtils {

    public static Object castType(String value, Class clazz) {

        if (clazz == int.class) {
            return Integer.valueOf(value);
        } else if (clazz == float.class) {
            return Float.valueOf(value);
        } else if (clazz == double.class) {
            return Double.valueOf(value);
        } else if (clazz == short.class) {
            return Short.valueOf(value);
        } else if (clazz == long.class) {
            return Long.valueOf(value);
        } else if (clazz == byte.class) {
            return Byte.valueOf(value);
        } else {
            return value;
        }
    }
}