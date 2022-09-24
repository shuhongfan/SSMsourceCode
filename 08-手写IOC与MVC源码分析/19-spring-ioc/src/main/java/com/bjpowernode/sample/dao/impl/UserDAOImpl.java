package com.bjpowernode.sample.dao.impl;

import com.bjpowernode.sample.dao.UserDAO;
import com.bjpowernode.spring.annotation.MyRepository;

@MyRepository
public class UserDAOImpl implements UserDAO {

    @Override
    public void sendSms() {
        System.out.println("业务方法执行，sendSms......");
    }
}