package com.bjpowernode.service;

import com.bjpowernode.mapper.AccountMapper;
import com.bjpowernode.model.Account;
import com.bjpowernode.plugin.pager.PageList;
import com.bjpowernode.plugin.pager.PageParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountMapper accountMapper;

    public Account getAccountById(Integer id) {
        return accountMapper.selectByPrimaryKey(id);
    }

    public List<Account> getAccount(Integer sex, String address) {
        return accountMapper.getAccount(sex, address);
    }

    public int updateAccount(Account account) {
        return accountMapper.updateByPrimaryKeySelective(account);
    }

    public PageList<Account> getAccountPage(PageParam pageParam, String address) {
        return accountMapper.selectByPage(pageParam, address);
    }
}