package vip.dengwj.account;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class DBUtil {
    private static final Properties properties;
    // 一个线程共享同一个 threadLocal
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

    // 连接
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

    // 开启事务
    public static void begin() {
        Connection connection;
        try {
            connection = getConnection();
            // 设置当前事务的自动提交为手动提交。开启事务
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // 提交事务
    public static void commit() {
        Connection connection = null;
        try {
            connection = getConnection();
            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(null, null, connection);
        }
    }

    // 回滚事务
    public static void rollback() {
        Connection connection = null;
        try {
            connection = getConnection();
            connection.rollback();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(null, null, connection);
        }
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
                // 关闭连接后，移除已关闭 connection 对象
                threadLocal.remove();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
