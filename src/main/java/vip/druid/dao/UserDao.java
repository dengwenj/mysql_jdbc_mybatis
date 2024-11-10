package vip.druid.dao;

import vip.druid.entity.User;

import java.util.List;

public interface UserDao {
    int insertUser(User user);
    int updateUser(User user);
    int deleteUser(Integer id);
    User selectUser(Integer id);
    List<User> selectUsers();

    long selectUserCount();
}
