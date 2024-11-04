package vip.dengwj.person;

public class Test {
    public static void main(String[] args) {
        insert();
    }

    public static void insert() {
        PersonDaoImpl personDao = new PersonDaoImpl();
        PersonEntity personEntity = new PersonEntity.Builder()
            .name("朴睦")
            .age(23)
            .email("33222@qq.com")
            .address("上海市浦东新区")
            .build();
        int res = personDao.insert(personEntity);
        if (res == 1) {
            System.out.println("新增成功");
        } else {
            System.out.println("新失败");
        }
    }
}
