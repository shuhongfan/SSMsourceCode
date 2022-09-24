package com.bjpowernode.plugin.pager;

import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;

import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 数据库方言
 * mysql、oracle、sqlserver
 *
 */
public class Dialect {

    public static BoundSql getBoundSql(MappedStatement mappedStatement, BoundSql boundSql, int offSet, String pageKey) throws SQLException {
        //获取数据库的元数据信息
        DatabaseMetaData dbmd = mappedStatement.getConfiguration().getEnvironment().getDataSource().getConnection().getMetaData();

        //获取到是什么数据库，如果是mysql，那么就limit分页，如果是Oracle，那么就rownum分页，sqlserver top
        String dbType = dbmd.getDatabaseProductName();
        String sql = null;
        if (dbType.equals("MySQL")) {
            //mysql数据库的分页  limit
            sql = getMySQLLimit(boundSql.getSql(), offSet);
        } else if (dbType.equals("Oracle")) {
            //TODO Oracle分页  rownum
        } else if (dbType.equals("SQLServer")) {
            //TODO SQLServer分页 top
        }
        //还有其他数据库没有实现 （省略......）

        //mybatis框架原来的参数映射 ： select * from account where id = ? and phone = ?
        //                           select * from account where id = ? and phone = ? limit ?, ?
        //                           select * from account where id = ? and phone = ? limit ?
        List<ParameterMapping> list = new ArrayList<ParameterMapping>(boundSql.getParameterMappings());
        if (offSet > 0) {
            //pageParam --> arg0.offset
            //pageParam --> arg0.rows
            list.add(new ParameterMapping.Builder(mappedStatement.getConfiguration(), pageKey + "offset", Integer.class).build());
            list.add(new ParameterMapping.Builder(mappedStatement.getConfiguration(), pageKey + "rows", Integer.class).build());
        } else {
            list.add(new ParameterMapping.Builder(mappedStatement.getConfiguration(), pageKey + "rows", Integer.class).build());
        }

        //得到一个新的BoundSql
        BoundSql newBoundSql = new BoundSql(mappedStatement.getConfiguration(), sql, list, boundSql.getParameterObject());

        return newBoundSql;
    }

    /**
     * mysql分页语句的拼装
     *
     * @param initSQL
     * @param offSet 从哪一行开始查
     * @return
     */
    public static String getMySQLLimit(String initSQL, int offSet) {
        StringBuffer stringBuffer = new StringBuffer(initSQL);
        if (offSet > 0) {
            stringBuffer.append(" limit ? , ?");
        } else {
            stringBuffer.append(" limit ?");
        }
        return stringBuffer.toString();
    }
}