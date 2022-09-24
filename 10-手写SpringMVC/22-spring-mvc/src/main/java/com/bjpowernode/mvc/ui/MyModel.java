package com.bjpowernode.mvc.ui;

import java.util.LinkedHashMap;

public class MyModel extends LinkedHashMap<String, Object> {

    public MyModel addAttribute(String attributeName, Object attributeValue) {
        put(attributeName, attributeValue);
        return this;
    }
}