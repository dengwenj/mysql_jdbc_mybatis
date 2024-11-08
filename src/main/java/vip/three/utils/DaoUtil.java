package vip.three.utils;

import vip.three.advanced.RowMapper;
import vip.three.entity.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 朴睦
 * @date 2024/11/8 22:22
 * @description 操作数据库封装
 */
public class DaoUtil<T> {
    /**
     * 公共的 增 删 改
     * @param sql sql 语句
     * @param args 占位符参数
     * @return 成功的行数
     */
    public int commonUpdate(String sql, Object... args) {
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

    public List<T> commonSelect(String sql, RowMapper<T> rowMapper, Object... args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        connection = DBUtil.getConnection();

        List<T> list = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);
            }
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(rowMapper.getRow(resultSet));
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(null, preparedStatement, resultSet);
        }
    }

    public static <V> List<V> commonSelectStatic(String sql, RowMapper<V> rowMapper, Object... args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        connection = DBUtil.getConnection();

        List<V> list = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);
            }
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(rowMapper.getRow(resultSet));
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(null, preparedStatement, resultSet);
        }
    }
}
