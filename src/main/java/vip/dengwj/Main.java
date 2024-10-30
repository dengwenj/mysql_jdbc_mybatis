package vip.dengwj;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // 1、注册驱动
        Class.forName("com.mysql.jdbc.Driver");

        String url = "jdbc:mysql://localhost:3306/companydb";
        String username = "pumu";
        String password = "123456";
        // 2、连接数据库（java 提供的连接到数据库）
        Connection connection = DriverManager.getConnection(url, username, password);
        if (connection != null) {
            System.out.println("连接数据库成功");

            // 3、获取发送 SQL 的对象
            Statement statement = connection.createStatement();

            // 4、编写 SQL  执行
            String sql = "insert into t_jobs(JOB_ID, JOB_TITLE, MIN_SALARY, MAX_SALARY) values('H5', 'html', '6000', '10000')";
            int res = statement.executeUpdate(sql);

            // 5. 处理结果
            if (res == 1) {
                System.out.println("添加成功");
            } else {
                System.out.println("添加失败");
            }

            // 6、释放资源
            statement.close();
            connection.close();
        } else {
            System.out.println("连接数据库失败");
        }
    }
}