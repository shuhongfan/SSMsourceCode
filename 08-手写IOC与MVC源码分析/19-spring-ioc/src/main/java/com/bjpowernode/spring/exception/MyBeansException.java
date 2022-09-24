package com.bjpowernode.spring.exception;

/**
 * 自定义bean的异常，是运行时异常
 *
 */
public class MyBeansException extends RuntimeException {

    public MyBeansException(String msg) {
        super(msg);
    }

    public MyBeansException(Throwable cause) {
        super(cause);
    }

    public MyBeansException(String msg, Throwable cause) {
        super(msg, cause);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}