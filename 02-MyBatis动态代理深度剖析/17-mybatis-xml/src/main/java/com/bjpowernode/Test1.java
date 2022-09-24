package com.bjpowernode;

import com.bjpowernode.mapper.AccountMapper;
import com.bjpowernode.model.Account;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * 测试mybatis框架
 */
@Slf4j //日志门面，没有实现的，实现我们现在用的是log4j2
public class Test1 {

    public static void main(String[] args) throws IOException {

        //TODO 第一步：读取mybatis-config.xml配置文件
        //InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        //InputStream inputStream = Test1.class.getClassLoader().getResourceAsStream("mybatis-config.xml");
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("mybatis-config.xml");

        //第二步：构建SqlSessionFactory (框架初始化) == new DefaultSqlSessionFactory持有一个Configuration的引用;
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //第三步：打开SqlSession
        SqlSession session = sqlSessionFactory.openSession();

        //第四步：获取Mapper接口对象 (底层是动态代理)
        AccountMapper accountMapper = session.getMapper(AccountMapper.class);

        //第五步：调用Mapper接口对象的方法操作数据库；
        Account account = accountMapper.selectByPrimaryKey(41);
        //account.setId(null);
        /*Account account = new Account();
        account.setIdCardType(new IdCardType("123456789123"));
        account.setAddress("湖北武当山");
        account.setMoney(new BigDecimal(99999));
        account.setRealname("张五侠");
        accountMapper.insertSelective(account);*/

        //第六步：业务处理
        log.info("查询结果>>: " + account.getIdCardType().getIdCard() + "--" + account.getRealname());

        //session提交并关闭
        session.commit();
        session.close();
    }
}