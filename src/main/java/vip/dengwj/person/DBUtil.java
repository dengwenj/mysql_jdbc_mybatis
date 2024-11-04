package vip.dengwj.person;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class DBUtil {
    private static final Properties properties;

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
        String url = properties.getProperty("url");
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        return DriverManager.getConnection(url, user, password);
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
