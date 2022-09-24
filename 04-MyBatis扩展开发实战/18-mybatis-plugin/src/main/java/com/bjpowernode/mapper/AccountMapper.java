package com.bjpowernode.mapper;

import com.bjpowernode.model.Account;
import com.bjpowernode.plugin.pager.PageList;
import com.bjpowernode.plugin.pager.PageParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AccountMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Account record);

    int insertSelective(Account record);

    Account selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Account record);

    PageList<Account> selectByPage(PageParam pageParam, @Param("address") String address);

    List<Account> getAccount(@Param("sex") Integer sex, @Param("address") String address);

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