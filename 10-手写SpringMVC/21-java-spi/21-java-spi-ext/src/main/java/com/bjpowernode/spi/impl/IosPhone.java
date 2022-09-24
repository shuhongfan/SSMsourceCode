package com.bjpowernode.spi.impl;

import com.bjpowernode.spi.Phone;

public class IosPhone implements Phone {

    @Override
    public void call() {
        System.out.println("Ios Phone call..........");
    }
}
