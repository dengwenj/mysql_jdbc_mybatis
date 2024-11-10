package vip.druid.dao.impl;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
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
        try {
            return queryRunner.query("select * from user where id = ?", new BeanHandler<User>(User.class), id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> selectUsers() {
        try {
            return queryRunner.query("select * from user", new BeanListHandler<User>(User.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public long selectUserCount() {
        try {
            return queryRunner.query("select count(*) from user", new ScalarHandler<Long>());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
