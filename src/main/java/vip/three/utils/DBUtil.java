package vip.three.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class DBUtil {
    private static final Properties properties = new Properties();
    private static final ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    static {
        InputStream is = DBUtil.class.getResourceAsStream("/db.properties");
        try {
            properties.load(is);
            String driver = properties.getProperty("driver");
            // 注册驱动
            Class.forName(driver);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() {
        Connection conn = threadLocal.get();
        if (conn == null) {
            String url = properties.getProperty("url");
            String user = properties.getProperty("user");
            String password = properties.getProperty("password");
            try {
                conn = DriverManager.getConnection(url, user, password);
                threadLocal.set(conn);
                return conn;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    // 开始事务
    public static void begin() {
        Connection connection = getConnection();
        if (connection != null) {
            try {
                connection.setAutoCommit(false);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    // 提交事务
    public static void commit() {
        Connection connection = null;
        try {
            connection = getConnection();
            assert connection != null;
            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(connection, null, null);
        }
    }

    // 回滚事务
    public static void rollback() {
        Connection connection = null;
        try {
            connection = getConnection();
            assert connection != null;
            connection.rollback();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(connection, null, null);
        }
    }

    // 释放资源
    public static void close(Connection connection, Statement statement, ResultSet resultSet) {
        try {
            if (connection != null) {
                connection.close();
                threadLocal.remove();
            }
            if (statement != null) {
                statement.close();
            }
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
