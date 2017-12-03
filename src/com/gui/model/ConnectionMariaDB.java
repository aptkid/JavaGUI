package com.gui.model;

import java.sql.*;
import java.sql.ResultSet;

public class ConnectionMariaDB {

    Connection connection = null;
    PreparedStatement preparedStatement;
    ResultSet resultSet;

    // MariaDB的JDBC URL编写方式：jdbc:mysql://主机名称：端口/数据库的名称?参数=值
    // 避免中文乱码要指定useUnicode和characterEncoding
    String url = "jdbc:mysql://127.0.0.1:3306/workers?" +
            "user=root&password=password&useUnicode=true&characterEncoding=UTF8";

    public ConnectionMariaDB () {
        try {
            //动态加载MariaDB驱动
            Class.forName("org.mariadb.jdbc.Driver");
            connection = DriverManager.getConnection(url);
        } catch (Exception e) {
            System.out.println("数据库连接失败" + e.toString());
        }
    }

    public ResultSet queryUser(String username){
        //不直接拼接字符串，防止SQL注入
        String sql = "select * from admin_info where username = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,username);
            resultSet = preparedStatement.executeQuery();
            return resultSet;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    public ResultSet insertUser(String username, String password) {
        String sql = "insert into admin_info values(?, ?)";
        try {

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();

            return resultSet;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public ResultSet getAll() {
        String sql = "select * from base_information";

        try {

            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            return resultSet;

        } catch (SQLException e) {
            System.out.println("获取全部数据出错");
        }
        return null;
    }

    public int getCount() {
        String sql = "select count(*) from base_information";

        try {

            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
