package vip.druid;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtil {
    // 声明连接池对象
    private static final DruidDataSource dataSource;

    static {
        Properties prop = new Properties();
        InputStream is = DBUtil.class.getResourceAsStream("/database.properties");
        try {
            prop.load(is);
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(prop);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // 获取连接
    public static Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
