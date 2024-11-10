package vip.druid;

import vip.druid.dao.UserDao;
import vip.druid.dao.impl.UserDaoImpl;
import vip.druid.entity.User;

import java.util.List;

public class InsertTest {
    public static void main(String[] args) {
        UserDao userDao = new UserDaoImpl();
        //User user = new User(null, "李雷", "123", "18000000000");
        //int res = userDao.insertUser(user);
        //System.out.println(res);
        User user = userDao.selectUser(1);
        System.out.println(user);

        List<User> users = userDao.selectUsers();
        System.out.println(users);

        long l = userDao.selectUserCount();
        System.out.println(l);
    }
}
