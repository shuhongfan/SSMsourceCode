package com.bjpowernode;

import com.bjpowernode.model.Account;

import java.sql.*;

public class Test3 {

    public static void main(String[] args) throws SQLException {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
        } catch(ClassNotFoundException e){
            System.out.println("找不到程序驱动类,加载驱动失败!");
            e.printStackTrace();
        }

        String url = "jdbc:oracle:thin:@192.168.31.50:1521:orcl";
        String user = "scott";
        String password = "123456";
        Connection conn = DriverManager.getConnection(url,user,password);

        String sql = "select * from users where id = ?";

        //1.执行静态语句,通常通过Statement实例实现。
        Statement st = conn.createStatement();
        //2.执行动态语句,通常通过PreparedStatement实现。
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, 1268);

        //3.执行数据库存储过程.通常通过CallableStatement实现
        CallableStatement cs = conn.prepareCall("{CALL mySp(?,?)}");

        //执行查询
        ps.execute();

        ResultSet resultSet = ps.getResultSet();

        Account account = new Account();

        while (resultSet.next()) {
            String address = resultSet.getString("address");
            account.setAddress(address);
        }




        ps.executeQuery();

        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {

        }

        //1.关闭记录集
        //2.关闭声明
        //3.关闭连接对象
        if(rs != null){
            rs.close();
        }
    }
}