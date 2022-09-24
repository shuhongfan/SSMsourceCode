package com.bjpowernode.spi.impl;

import com.bjpowernode.spi.Phone;

public class AndroidPhone implements Phone {

    @Override
    public void call() {
        System.out.println("Android Phone call..........");
    }
}