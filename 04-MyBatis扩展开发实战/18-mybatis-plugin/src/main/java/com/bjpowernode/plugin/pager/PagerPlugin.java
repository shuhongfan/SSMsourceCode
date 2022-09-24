package com.bjpowernode.plugin.pager;

import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

/**
 * MyBatis分页拦截器（插件）
 *
 */
@Intercepts(value = {
        @Signature(
                type = Executor.class,
                method = "query",
                args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}
        ),
        @Signature(
                type = Executor.class,
                method = "query",
                args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class}
        )
})
public class PagerPlugin implements Interceptor {

    //是否要开启分页
    private boolean isEnable;

    /**
     * 对查询方法拦截
     *
     * @param invocation
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        //判断是否需要拦截，检查开关isEnable=true ?
        if (isEnable) {
            //拦截mybatis里面Executor的query方法
            //<E> List<E> query(MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler)
            Object[] queryArgs = invocation.getArgs();
            MappedStatement mappedStatement = (MappedStatement)queryArgs[0];
            Object parameter = queryArgs[1];

            //分页参数的一个类
            PageParam pageParam = null;
            String paramKey = "";

            if (parameter instanceof PageParam) {
                pageParam = (PageParam)parameter;
                //mybatis框架当你传多个参数的时候，mybatis框架会把参数转成map
            } else if (parameter instanceof HashMap) {
                HashMap<String, Object> parameterMap = (HashMap<String, Object>)parameter;
                for (String key : parameterMap.keySet()) {
                    //{"id" : 1267, "phone" : "137220200000"}
                    Object value = parameterMap.get(key);
                    if (value instanceof PageParam) {
                        pageParam = (PageParam)value;
                        paramKey = key + "."; // phone.     idcard.
                        System.out.println(paramKey);
                        break;
                    }
                }
            }

            if (pageParam != null) {
                //当前是第几页
                int index = pageParam.getIndex();
                //每页显示多少条
                int rows = pageParam.getRows();

                //拿到原始的sql查询语句
                BoundSql boundSql = mappedStatement.getBoundSql(parameter);

                //要做分页查询，首先需要查询总数据条数，以便于计算分页
                int total = queryTotalByPage(mappedStatement, parameter, boundSql);

                //分页查询每页的数据
                List resultList = Collections.EMPTY_LIST;
                //查询真实的当前分页的数据
                if (total > 0) {
                    //数据库有数据的情况
                    BoundSql newBoundSql = Dialect.getBoundSql(mappedStatement, boundSql, pageParam.getOffset(), paramKey);

                    //开始去调用目标方法
                    //<E> List<E> query(MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler)
                    //在调用之前，需要将 MappedStatement ms 对象中的SQL信息改掉，改成分页的sql
                    MappedStatement newMappedStatement = buildMappedStatement(mappedStatement, new MySqlSource(newBoundSql));

                    //把原来方法的第一个参数替换成我们新的
                    queryArgs[0] = newMappedStatement;

                    //真正滴调用目标方法，但是里面的参数被我们改掉了
                    resultList = (List)invocation.proceed();
                }
                //数据库没有数据的情况
                return new PageList(resultList, index, rows, total);
            }
        }

        //不需要拦截,让他直接执行原来的代码即可
        return invocation.proceed();
    }

    /**
     * 产生一个代理对象
     *
     * @param target
     * @return
     */
    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    /**
     * mybatis框架在启动的时候会执行该方法，读取配置的属性信息
     *
     * @param properties
     */
    @Override
    public void setProperties(Properties properties) {
        String enable = properties.getProperty("isEnable");
        isEnable = (enable != null ? Boolean.parseBoolean(enable) : true);
    }

    /**
     * 查询总条数
     *
     * @param mappedStatement
     * @param parameter
     * @param boundSql
     * @return
     */
    public int queryTotalByPage(MappedStatement mappedStatement, Object parameter, BoundSql boundSql) {
        //拼装查询总数的SQL
        StringBuffer sql = new StringBuffer();
        //select count(1) from (select * from u_user_info) t
        sql.append("select count(1) from (");
        sql.append(boundSql.getSql());
        sql.append(") t");

        //封装了一下jdbc
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int total = 0;
        try {
            connection = mappedStatement.getConfiguration().getEnvironment().getDataSource().getConnection();
            preparedStatement = connection.prepareStatement(sql.toString());
            //设置参数
            ParameterHandler parameterHandler = new DefaultParameterHandler(mappedStatement, parameter, boundSql);
            parameterHandler.setParameters(preparedStatement);

            //获取结果集
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                total = resultSet.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return total;
    }

    /**
     * 构建MappedStatement对象
     *
     * @param ms
     * @param mySqlSource
     * @return
     */
    public MappedStatement buildMappedStatement (MappedStatement ms, MySqlSource mySqlSource) {
        MappedStatement.Builder builder = new MappedStatement.Builder(ms.getConfiguration(), ms.getId(), mySqlSource, ms.getSqlCommandType());
        builder.resource(ms.getResource());
        builder.fetchSize(ms.getFetchSize());
        builder.statementType(ms.getStatementType());

        if (ms.getKeyProperties() != null && ms.getKeyProperties().length != 0) {
            StringBuffer keyProperties = new StringBuffer();
            String[] keyProp;
            int length = (keyProp = ms.getKeyProperties()).length;
            for (int i = 0; i < length; ++i) {
                String keyProperty = keyProp[i];
                keyProperties.append(keyProperty).append(",");
            }
            keyProperties.delete(keyProperties.length() - 1, keyProperties.length());
            builder.keyProperty(keyProperties.toString());
        }

        builder.timeout(ms.getTimeout());
        builder.parameterMap(ms.getParameterMap());
        builder.resultMaps(ms.getResultMaps());
        builder.resultSetType(ms.getResultSetType());
        builder.cache(ms.getCache());
        builder.flushCacheRequired(ms.isFlushCacheRequired());
        builder.useCache(ms.isUseCache());

        //构建器模式
        return builder.build();
    }
}