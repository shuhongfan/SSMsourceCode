package com.bjpowernode;

import com.bjpowernode.model.Account;
import com.bjpowernode.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.List;

/**
 * 测试mybatis框架
 */
@Slf4j //日志门面，没有实现的，实现我们现在用的是log4j2
public class Test {

    public static void main(String[] args) throws IOException {

        ApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        AccountService accountService = context.getBean(AccountService.class);

        List<Account> account = accountService.getAccount(1, "湖北武当山");
        account.forEach(acc -> System.out.println(acc.getRealname()));

        /*
        int update = accountService.updateAccount(account.get(0));
        System.out.println("更新：" + update);*/

        String address = "湖北武当山";
        /*PageList<Account> page = accountService.getAccountPage(new PageParam(3, 15), address);

        page.getIndex();
        page.getRows();
        page.getTotal();

        page.forEach(acc -> System.out.println(acc.getRealname()));*/

        //int update = accountService.updateAccount(account);
        //System.out.println("更新：" + update);
    }
}