package vip.dengwj;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DeleteJdbc {
    public static void main(String[] args) throws Exception {
        // 1、加载 mysql 驱动
        Class.forName("com.mysql.jdbc.Driver");

        // 2、获取连接
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/companydb", "pumu", "123456");

        if (connection != null) {
            System.out.println("连接成功");
            // 3、创建语句对象
            Statement statement = connection.createStatement();

            // 4、书写 SQL，执行
            int res = statement.executeUpdate("delete from t_jobs where JOB_ID = 'H5'");

            // 5、处理结果
            if (res > 0) {
                System.out.println("删除成功");
            } else {
                System.out.println("删除失败");
            }

            // 6、释放资源
            statement.close();
            connection.close();
        } else {
            System.out.println("连接失败");
        }
        //
    }
}
