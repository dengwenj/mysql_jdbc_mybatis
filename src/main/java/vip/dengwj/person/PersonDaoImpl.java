package vip.dengwj.person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * @date 2024/11/4 22:57
 * @author 朴睦
 * @description 只做关于数据库相关的操作，逻辑处理不再这里做
 */
public class PersonDaoImpl {
    // 新增
    public int insert(PersonEntity person) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DBUtil.getConnection();
            String sql = "insert into person (name, age, bornDate, email, address) values (?, ?, ?, ?, ?);";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, person.getName());
            preparedStatement.setInt(2, person.getAge());
            preparedStatement.setDate(3, null);
            preparedStatement.setString(4, person.getEmail());
            preparedStatement.setString(5, person.getAddress());

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(null, preparedStatement, connection);
        }
    }
    // 更新
    public int update(PersonEntity person) {
        return 0;
    }
    // 删除
    public int delete(int id) {
        return 0;
    }
    public PersonEntity select(int id) {
        return null;
    }
    public List<PersonEntity> selectAll() {
        return null;
    }
}
