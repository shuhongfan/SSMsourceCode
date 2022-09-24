package com.bjpowernode.ioc.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

/**
 * 反射工具类
 *
 */
public class MyReflectionUtils {

    public static <T> Constructor<T> accessibleConstructor(Class<T> clazz, Class<?>... parameterTypes)
            throws NoSuchMethodException {

        Constructor<T> ctor = clazz.getDeclaredConstructor(parameterTypes);
        makeAccessible(ctor);
        return ctor;
    }

    public static void makeAccessible(Constructor<?> ctor) {
        if ((!Modifier.isPublic(ctor.getModifiers()) ||
                !Modifier.isPublic(ctor.getDeclaringClass().getModifiers())) && !ctor.isAccessible()) {
            ctor.setAccessible(true);
        }
    }
}
