package com.bjpowernode.spi.impl;

import com.bjpowernode.spi.Phone;

public class DefaultPhone implements Phone {

    @Override
    public void call() {
        System.out.println("Default Phone call..........");
    }
}