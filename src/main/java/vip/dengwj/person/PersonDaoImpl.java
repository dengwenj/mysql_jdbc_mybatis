package vip.dengwj.person;

import java.sql.*;
import java.util.ArrayList;
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
            preparedStatement.setDate(3, DateUtil.utilDateToSqlDate(person.getBornDate()));
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
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DBUtil.getConnection();
            String sql = "update person set name = ?, age = ?, bornDate = ?, email = ?, address = ? where id = ?;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, person.getName());
            preparedStatement.setInt(2, person.getAge());
            preparedStatement.setDate(3, null);
            preparedStatement.setString(4, person.getEmail());
            preparedStatement.setString(5, person.getAddress());
            preparedStatement.setInt(6, person.getId());
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(null, preparedStatement, connection);
        }
    }

    // 删除
    public int delete(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DBUtil.getConnection();
            String sql = "delete from person where id = ?;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(null, preparedStatement, connection);
        }
    }

    // 查询单个
    public PersonEntity select(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DBUtil.getConnection();
            String sql = "select * from person where id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int id1 = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                Date bornDate = resultSet.getDate("bornDate");
                String email = resultSet.getString("email");
                String address = resultSet.getString("address");

                return new PersonEntity.Builder()
                    .id(id1)
                    .name(name)
                    .age(age)
                    .bornDate(bornDate)
                    .email(email)
                    .address(address)
                    .build();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(resultSet, preparedStatement, connection);
        }
        return null;
    }

    // 查询多个
    public List<PersonEntity> selectAll() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DBUtil.getConnection();
            String sql = "select * from person;";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            List<PersonEntity> list = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                Date bornDate = resultSet.getDate("bornDate");
                String email = resultSet.getString("email");
                String address = resultSet.getString("address");
                list.add(
                    new PersonEntity.Builder()
                        .id(id)
                        .name(name)
                        .age(age)
                        .bornDate(bornDate)
                        .email(email)
                        .address(address)
                        .build()
                );
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(resultSet, preparedStatement, connection);
        }
    }

    public PersonEntity select(String name) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement("select * from person where name = ?;");
            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();

            PersonEntity person = null;
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String pName = resultSet.getString("name");
                int age = resultSet.getInt("age");
                Date bornDate = resultSet.getDate("bornDate");
                String email = resultSet.getString("email");
                String address = resultSet.getString("address");

                person = new PersonEntity.Builder()
                    .id(id)
                    .name(pName)
                    .age(age)
                    .bornDate(bornDate)
                    .email(email)
                    .address(address)
                    .build();
            }
            return person;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(resultSet, preparedStatement, connection);
        }
    }
}
