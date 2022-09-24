package com.bjpowernode.mapper;

import com.bjpowernode.model.Account;

import java.util.List;

public interface AccountMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Account record);

    int insertSelective(Account record);

    Account selectByPrimaryKey(Integer id);

    List<Account> selectByPage();

    int updateByPrimaryKeySelective(Account record);

    int updateByPrimaryKey(Account record);

    /**
     * JDK1.8开始，接口中允许定义默认方法
     *
     * @param id
     * @return
     */
    default Account getAccountById(Integer id) {
        return new Account();
    }
}