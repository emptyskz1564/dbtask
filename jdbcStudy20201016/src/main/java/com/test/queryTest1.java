package com.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * 创建时间: 2020-10-16 9:16
 * 文件备注:
 * 编写人员: 杨伯益
 */


public class queryTest1 {
    public static final String URL = "jdbc:mysql://139.196.86.99:3306/test?useUnicode=true&characterEncoding=UTF-8&useSSL=false";
    public static final String USER = "root";
    public static final String PASSWORD = "131428";

    public static void main(String[] args) throws Exception {
        //1.加载驱动程序
        Class.forName("com.mysql.cj.jdbc.Driver");
        //2. 获得数据库连接
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        //3.操作数据库，实现增删改查
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT name, age FROM Student");
        //如果有数据，rs.next()返回true
        while(rs.next()){
            System.out.println(rs.getString("name")+" 年龄："+rs.getInt("age"));
        }
    }
}
