package vip.dengwj;

import vip.dengwj.util.DBUtil2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Login3 {
    public static void main(String[] args) throws Exception {
        // 通过控制台用户输入用户名和密码
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入用户名:");
        String username = scanner.next();
        System.out.println("请输入密码:");
        String password = scanner.next();

        Connection connection = DBUtil2.getConnection();
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

        DBUtil2.close(resultSet, preparedStatement, connection);
    }
}
