package vip.three.view;

import vip.three.advanced.RowMapper;
import vip.three.entity.Person;
import vip.three.utils.DaoUtil;

import java.sql.Date;
import java.util.List;

public class TestPerson {
    public static void main(String[] args) {
        DaoUtil<Person> personDaoUtil = new DaoUtil<>();

        //RowMapper<Person> rowMapper = new RowMapper<Person>() {
        //    @Override
        //    public Person getRow(ResultSet rs) throws SQLException {
        //        return null;
        //    }
        //};

        RowMapper<Person> rowMapper = rs -> {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            int age = rs.getInt("age");
            Date borndate = rs.getDate("borndate");
            String email = rs.getString("email");
            String address = rs.getString("address");

            return new Person(id, name, age, borndate, email, address);
        };
        String sql = "select * from person where id = ?";
        List<Person> people = personDaoUtil.commonSelect(sql, rowMapper, 2);
        System.out.println(people);
    }
}
