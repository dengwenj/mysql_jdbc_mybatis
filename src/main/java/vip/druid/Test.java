package vip.druid;

import java.sql.Connection;
import java.sql.SQLException;

public class Test {
    public static void main(String[] args) throws SQLException {
        for (int i = 0; i < 20; i++) {
            Connection connection = DBUtil.getConnection();
            System.out.println(connection);
            // 关闭 -> 这里的关闭是放回连接池
            connection.close();
        }
    }
}
