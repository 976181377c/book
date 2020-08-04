package com.javaweb;

import java.sql.*;

public class LinkDb {
    public static final String url = "jdbc:mysql://localhost:3306/book?characterEncoding=utf8";
    public static final String name = "com.mysql.jdbc.Driver";
    public static final String user = "root";
    public static final String password = "";
    public static Connection conn = null;

    public static Connection getConnection() {
        try {
            Class.forName(name);// 指定连接类型
            conn = DriverManager.getConnection(url, user, password);// 获取连接
           
           
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public void close() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}