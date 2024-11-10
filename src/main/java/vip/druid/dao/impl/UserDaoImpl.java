package vip.druid.dao.impl;

import org.apache.commons.dbutils.QueryRunner;
import vip.druid.DBUtil;
import vip.druid.dao.UserDao;
import vip.druid.entity.User;

import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private final QueryRunner queryRunner;

    public UserDaoImpl() {
        queryRunner = new QueryRunner(DBUtil.getDataSource());
    }

    @Override
    public int insertUser(User user) {
        String sql = "insert into user(username, password, phone) values(?, ?, ?)";
        Object[] params = {user.getUsername(), user.getPassword(), user.getPhone()};
        try {
            return queryRunner.update(sql, params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int updateUser(User user) {
        return 0;
    }

    @Override
    public int deleteUser(Integer id) {
        return 0;
    }

    @Override
    public User selectUser(Integer id) {
        return null;
    }

    @Override
    public List<User> selectUsers() {
        return List.of();
    }
}
