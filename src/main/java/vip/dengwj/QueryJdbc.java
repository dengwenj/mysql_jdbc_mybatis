package vip.dengwj;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class QueryJdbc {
    public static void main(String[] args) throws Exception {
        // 1、注册驱动
        Class.forName("com.mysql.jdbc.Driver");
        // 2、获取连接
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/companydb", "pumu", "123456");
        // 3、创建语句
        Statement statement = connection.createStatement();
        // 4、执行 SQL
        ResultSet resultSet = statement.executeQuery("select * from t_jobs");
        // 5、执行结果
        while (resultSet.next()) {
            System.out.println("通过编号找:");
            String jobId = resultSet.getString(1);
            String jobTitle = resultSet.getString(2);
            String minSalary = resultSet.getString(3);
            String maxSalary = resultSet.getString(4);
            System.out.println(jobId + ":" + jobTitle + ":" + minSalary + ":" + maxSalary);

            System.out.println("通过列名找:");
            String string = resultSet.getString("job_id");
            String string1 = resultSet.getString("job_title");
            String string2 = resultSet.getString("min_salary");
            String string3 = resultSet.getString("max_salary");
            System.out.println(string + ":" + string1 + ":" + string2 + ":" + string3);
            System.out.println("------------------------------------");
        }
        // 6、释放资源
        resultSet.close();
        statement.close();
        connection.close();
    }
}
