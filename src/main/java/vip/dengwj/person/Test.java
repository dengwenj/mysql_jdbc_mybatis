package vip.dengwj.person;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        //insert();
        //update();
        //delete();
        //select();
        selectAll();
    }

    public static void insert() {
        PersonDaoImpl personDao = new PersonDaoImpl();
        PersonEntity personEntity = new PersonEntity.Builder()
            .name("pumu")
            .age(24)
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

    public static void update() {
        PersonDaoImpl personDao = new PersonDaoImpl();
        int update = personDao.update(
            new PersonEntity.Builder()
                .id(1)
                .name("李雷")
                .age(23)
                .email("1111@qq.com")
                .bornDate(null)
                .address("上海市浦东新区")
                .build()
        );
        if (update == 1) {
            System.out.println("更新成功");
        } else {
            System.out.println("更新失败");
        }
    }

    public static void delete() {
        PersonDaoImpl personDao = new PersonDaoImpl();
        int delete = personDao.delete(1);
        if (delete == 1) {
            System.out.println("删除成功");
        } else {
            System.out.println("删除失败");
        }
    }

    public static void select() {
        PersonDaoImpl personDao = new PersonDaoImpl();
        PersonEntity select = personDao.select(2);
        if (select != null) {
            System.out.println(select);
        }
    }

    public static void selectAll() {
        PersonDaoImpl personDao = new PersonDaoImpl();
        List<PersonEntity> selectAll = personDao.selectAll();
        for (PersonEntity personEntity : selectAll) {
            System.out.println(personEntity);
            if (personEntity.getBornDate() != null) {
                System.out.println(personEntity.getBornDate().toString());
            }
        }
    }
}
