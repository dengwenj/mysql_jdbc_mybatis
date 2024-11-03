package vip.dengwj.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @date 2024/11/3 22:56
 * @author 朴睦
 * @description 获取连接、关闭资源
 */
public class DBUtil2 {
    private static final Connection connection;

    static {
        Properties prop = new Properties();
        try {
            InputStream resourceAsStream = DBUtil2.class.getResourceAsStream("/db.properties");
            System.out.println(resourceAsStream + "哈哈哈");
            prop.load(resourceAsStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            String driver = prop.getProperty("driver");
            Class.forName(driver);
            String url = prop.getProperty("url");
            String user = prop.getProperty("user");
            String password = prop.getProperty("password");
            connection = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() {
        return connection;
    }

    public static void close(ResultSet rs, Statement stmt, Connection conn) {
        try {
            if (rs != null) {
                rs.close();
            }

            if (stmt != null) {
                stmt.close();
            }

            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
