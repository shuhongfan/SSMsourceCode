package com.bjpowernode.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Account {

    private Integer id;

    private Integer uid;

    private String realname;

    private String idcard;

    private IdCardType idCardType;

    private Integer sex;

    private String address;

    private BigDecimal money;
}