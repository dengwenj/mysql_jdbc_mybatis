package vip.dengwj.person;


import java.util.Date;

public class PersonEntity {
    private Integer id;
    private String name;
    private Integer age;
    private Date bornDate;
    private String email;
    private String address;

    public PersonEntity() {
    }

    public PersonEntity(Integer id, String name, Integer age, Date bornDate, String email, String address) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.bornDate = bornDate;
        this.email = email;
        this.address = address;
    }

    public PersonEntity(Builder builder) {
        this.name = builder.name;
        this.age = builder.age;
        this.bornDate = builder.bornDate;
        this.email = builder.email;
        this.address = builder.address;
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

    public static class Builder {
        private String name;
        private Integer age;
        private Date bornDate;
        private String email;
        private String address;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder age(Integer age) {
            this.age = age;
            return this;
        }

        public Builder bornDate(Date bornDate) {
            this.bornDate = bornDate;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder address(String address) {
            this.address = address;
            return this;
        }

        public PersonEntity build() {
            return new PersonEntity(this);
        }
    }
}
