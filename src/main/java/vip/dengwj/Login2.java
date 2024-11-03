package vip.dengwj;

import java.sql.*;
import java.util.Scanner;

public class Login2 {
    public static void main(String[] args) throws Exception {
        // 通过控制台用户输入用户名和密码
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入用户名:");
        String username = scanner.next();
        System.out.println("请输入密码:");
        String password = scanner.next();

        // 注入驱动
        Class.forName("com.mysql.jdbc.Driver");
        // 获取连接
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/companydb", "pumu", "123456");
        // 获得 PreparedStatement 对象，预编译 SQL 语句
        PreparedStatement preparedStatement = connection.prepareStatement("select * from user where username=? and password=?");
        // ? 占位符
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);

        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            System.out.println("登录成功");
        } else {
            System.out.println("登录失败");
        }

        resultSet.close();
        preparedStatement.close();
        connection.close();
    }
}
