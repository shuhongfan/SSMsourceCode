package com.bjpowernode;

public class Test2 {

    public static void main(String[] args) {
        //classpath
        System.out.println(Test2.class.getClassLoader());
        System.out.println(Test2.class.getClassLoader().getParent());
        System.out.println(Test2.class.getClassLoader().getParent().getParent());
        //rt.jar
        System.out.println(String.class.getClassLoader());

        //系统的classloader
        System.out.println(ClassLoader.getSystemClassLoader());

        System.out.println(Test2.class.getClassLoader());
    }
}