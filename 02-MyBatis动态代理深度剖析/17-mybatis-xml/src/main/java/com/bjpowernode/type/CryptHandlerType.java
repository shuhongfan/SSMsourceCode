package com.bjpowernode.type;

import com.bjpowernode.model.IdCardType;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

/**
 * 自定义一个对身份证加密和解密的类型装换器
 *
 */
public class CryptHandlerType implements TypeHandler<IdCardType> {

    /**
     * 设置参数的时候，mybatis框架会调用该方法
     *
     * @param ps
     * @param i
     * @param parameter
     * @param jdbcType
     * @throws SQLException
     */
    @Override
    public void setParameter(PreparedStatement ps, int i, IdCardType parameter, JdbcType jdbcType) throws SQLException {
        //加密后的身份证号码，使用base64加密的
        String cryptIdCard = Base64.getEncoder().encodeToString(parameter.getIdCard().getBytes());
        ps.setString(i, cryptIdCard);
    }

    @Override
    public IdCardType getResult(ResultSet rs, String columnName) throws SQLException {
        //加密的身份证号码
        String cryptIdCard = rs.getString(columnName);
        //解密的身份证号码
        String idCard = new String(Base64.getDecoder().decode(cryptIdCard));
        return new IdCardType(idCard);
    }

    @Override
    public IdCardType getResult(ResultSet rs, int columnIndex) throws SQLException {
        //加密的身份证号码
        String cryptIdCard = rs.getString(columnIndex);
        //解密的身份证号码
        String idCard = new String(Base64.getDecoder().decode(cryptIdCard));
        return new IdCardType(idCard);
    }

    @Override
    public IdCardType getResult(CallableStatement cs, int columnIndex) throws SQLException {
        //加密的身份证号码
        String cryptIdCard = cs.getString(columnIndex);
        //解密的身份证号码
        String idCard = new String(Base64.getDecoder().decode(cryptIdCard));
        return new IdCardType(idCard);
    }
}