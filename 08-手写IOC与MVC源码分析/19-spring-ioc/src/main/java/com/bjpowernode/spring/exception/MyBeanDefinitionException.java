package com.bjpowernode.spring.exception;

public class MyBeanDefinitionException extends RuntimeException {

    public MyBeanDefinitionException(String msg) {
        super(msg);
    }

    public MyBeanDefinitionException( Throwable cause) {
        super(cause);
    }

    public MyBeanDefinitionException( String msg,  Throwable cause) {
        super(msg, cause);
    }

    @Override
    
    public String getMessage() {
        return super.getMessage();
    }
}
