package com.bjpowernode.model;

import lombok.Data;

import java.util.Date;

@Data
public class Users {

    private Integer id;

    private String nick;

    private String phone;

    private String password;

    private String email;

    private String account;

    private Date createTime;

    private String mark;
}