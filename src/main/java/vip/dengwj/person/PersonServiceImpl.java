package vip.dengwj.person;

public class PersonServiceImpl {
    // 注册
    public void register(PersonEntity person) {
        PersonDaoImpl personDao = new PersonDaoImpl();
        // 先查询用户是否存在
        PersonEntity p = personDao.select(person.getName());
        if (p == null) {
            // 不存在新增
            int insert = personDao.insert(person);
            if (insert == 1) {
                System.out.println("注册成功");
            } else {
                System.out.println("注册失败");
            }
            return;
        }
        // 存在返回该用户名已注册
        System.out.println("该用户名已注册");
    }
}
