package com.bjpowernode.plugin.readwrite;

import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.keygen.SelectKeyGenerator;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.Properties;

/**
 * 读写分离插件
 *
 */
@Intercepts(value = {
        @Signature(
                type = Executor.class, //要拦截哪个接口
                method = "update",
                args = {
                        MappedStatement.class, Object.class
                }),
        @Signature(
                type = Executor.class,
                method = "query",
                args = {
                        MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class
                }),
        @Signature(
                type = Executor.class,
                method = "query",
                args = {
                        MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class
                })
})
public class ReadWritePlugin implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        //获取到目标类要执行的那个方法的参数
        Object[] objects = invocation.getArgs();
        MappedStatement ms = (MappedStatement) objects[0];

        //数据源类型：主库、从库
        DataSourceType dataSourceType = null;
        //类型为查询
        if (ms.getSqlCommandType().equals(SqlCommandType.SELECT)) {
            // !selectKey为自增id查询主键(SELECT LAST_INSERT_ID())方法，使用主库
            if (ms.getId().contains(SelectKeyGenerator.SELECT_KEY_SUFFIX)) {
                //使用主库：写库
                dataSourceType = DataSourceType.WRITE;
            } else {
                //使用从库：读库
                dataSourceType = DataSourceType.READ;
            }
        } else {
            //类型为增、删、改
            dataSourceType = DataSourceType.WRITE;
        }

        //修改当前线程要选择的数据源的key；
        DataSourceHolder.setDataSourceType(dataSourceType);

        //最终调用目标类的方法
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        if (target instanceof Executor) {
            return Plugin.wrap(target, this);
        } else {
            return target;
        }
    }

    @Override
    public void setProperties(Properties properties) {

    }
}