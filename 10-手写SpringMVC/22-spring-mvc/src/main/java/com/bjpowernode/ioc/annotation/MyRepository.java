package com.bjpowernode.ioc.annotation;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyRepository {

    String value() default "";
}
