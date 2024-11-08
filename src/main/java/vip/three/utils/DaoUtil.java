package vip.three.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author 朴睦
 * @date 2024/11/8 22:22
 * @description 操作数据库封装
 */
public class DaoUtil {
    /**
     * 公共的 增 删 改
     * @param sql sql 语句
     * @param args 占位符参数
     * @return 成功的行数
     */
    public static int commonUpdate(String sql, Object... args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        connection = DBUtil.getConnection();
        try {
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);
            }
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(null, preparedStatement, null);
        }
    }
}
