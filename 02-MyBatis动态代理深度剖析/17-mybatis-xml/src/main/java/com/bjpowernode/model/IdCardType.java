package com.bjpowernode.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor //没有参数的构造方法
@AllArgsConstructor //所有参数的构造方法
@Data
public class IdCardType implements Serializable {

    private String idCard;

}