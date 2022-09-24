package com.bjpowernode.spi;

import com.bjpowernode.spi.impl.AndroidPhone;
import com.bjpowernode.spi.impl.DefaultPhone;
import com.bjpowernode.spi.impl.IosPhone;

import java.util.ServiceLoader;

public class Test {

    public static void main(String[] args) {

        //ServiceLoader<ServletContainerInitializer> serviceLoader = ServiceLoader.load(ServletContainerInitializer.class);

        ServiceLoader<Phone> serviceLoader = ServiceLoader.load(Phone.class);

        serviceLoader.forEach((Phone phone) -> {
            if (phone instanceof DefaultPhone) {
                //默认是实现
                phone.call();
            }
            if (phone instanceof AndroidPhone) {
                //android的实现
                phone.call();
            }
            if (phone instanceof IosPhone) {
                //ios的实现
                phone.call();
            }
        });
    }
}