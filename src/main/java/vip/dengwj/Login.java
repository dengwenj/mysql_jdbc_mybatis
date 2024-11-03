package vip.dengwj;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Login {
    public static void main(String[] args) throws Exception {
        // 注入驱动
        Class.forName("com.mysql.jdbc.Driver");
        // 获取连接
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/companydb", "pumu", "123456");
        // 执行语句
        Statement statement = connection.createStatement();

        // 通过控制台用户输入用户名和密码
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入用户名:");
        String username = scanner.next();
        String un = "'" + username + "'";

        System.out.println("请输入密码:");
        String password = scanner.next();
        String pw = "'" + password + "'";

        String sql = "select * from user where username = " + un + " and password = " + pw + " ";
        ResultSet resultSet = statement.executeQuery(sql);
        if (resultSet.next()) {
            System.out.println("登录成功");
        } else {
            System.out.println("登录失败");
        }

        resultSet.close();
        statement.close();
        connection.close();
    }
}
