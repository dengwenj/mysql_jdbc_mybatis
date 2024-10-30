package vip.dengwj;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // 注册驱动
        Class.forName("com.mysql.jdbc.Driver");

        String url = "jdbc:mysql://localhost:3306/companydb";
        String username = "root";
        String password = "123456";
        // 连接数据库（java 提供的连接到数据库）
        Connection connection = DriverManager.getConnection(url, username, password);
        if (connection != null) {
            System.out.println("连接数据库成功");
        } else {
            System.out.println("连接数据库失败");
        }
    }
}