package com.bjpowernode.plugin;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;

import java.sql.Connection;
import java.util.Properties;

/**
 * 读写分离插件
 *
 *  session.selcetxxx() --> executor.query()  --> StatementHandler.query()
 */
@Intercepts(value = {
        @Signature(
                type = StatementHandler.class, //要拦截哪个接口
                method = "prepare",
                args = {
                        Connection.class, Integer.class
                })
})
public class MyPlugin implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        StatementHandler statementHandler = (StatementHandler)invocation.getTarget();

        //获取到目标类要执行的那个方法的参数
        Object[] objects = invocation.getArgs();
        Connection statements = (Connection)objects[0];

        System.out.println("sql日志：" + statementHandler.getBoundSql().getSql());

        long start = System.currentTimeMillis();

        //最终调用目标类的方法
        Object proceed = invocation.proceed();

        long end = System.currentTimeMillis();
        System.out.println("sql执行花费的时间：" + (end - start));

        return proceed;
    }

    @Override
    public Object plugin(Object target) {
        if (target instanceof StatementHandler) {
            return Plugin.wrap(target, this);
        } else {
            return target;
        }
    }

    @Override
    public void setProperties(Properties properties) {

    }
}