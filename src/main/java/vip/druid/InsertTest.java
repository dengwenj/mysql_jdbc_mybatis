package vip.druid;

import vip.druid.dao.UserDao;
import vip.druid.dao.impl.UserDaoImpl;
import vip.druid.entity.User;

public class InsertTest {
    public static void main(String[] args) {
        UserDao userDao = new UserDaoImpl();
        User user = new User(null, "李雷", "123", "18000000000");
        int res = userDao.insertUser(user);
        System.out.println(res);
    }
}
