package vip.dengwj.account;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class DBUtil {
    private static final Properties properties;
    private static final ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    static {
        try {
            InputStream is = DBUtil.class.getResourceAsStream("/db.properties");
            System.out.println(is);
            properties = new Properties();
            properties.load(is);
            String driver = properties.getProperty("driver");

            Class.forName(driver);
        } catch (ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() throws SQLException {
        // 将当前线程中绑定的 Connection 对象赋值给 connection
        Connection connection = threadLocal.get();

        String url = properties.getProperty("url");
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        if (connection == null) {
            connection = DriverManager.getConnection(url, user, password);
            // 把连接存在当前线程共享中
            threadLocal.set(connection);
        }
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
