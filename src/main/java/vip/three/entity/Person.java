package vip.three.entity;

import java.util.Date;

/**
 * @date 2024/11/6 23:33
 * @author 朴睦
 * @description 实体类
 */
public class Person {
    private Integer id;
    private String name;
    private Integer age;
    private Date bornDate;
    private String email;
    private String address;

    public Person(Integer id, String name, Integer age, Date bornDate, String email, String address) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.bornDate = bornDate;
        this.email = email;
        this.address = address;
    }

    /**
     * 获取
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取
     * @return age
     */
    public Integer getAge() {
        return age;
    }

    /**
     * 设置
     * @param age
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * 获取
     *
     * @return bornDate
     */
    public Date getBornDate() {
        return bornDate;
    }

    /**
     * 设置
     * @param bornDate
     */
    public void setBornDate(Date bornDate) {
        this.bornDate = bornDate;
    }

    /**
     * 获取
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    public String toString() {
        return "PersonEntity{id = " + id + ", name = " + name + ", age = " + age + ", bornDate = " + bornDate + ", email = " + email + ", address = " + address + "}";
    }
}