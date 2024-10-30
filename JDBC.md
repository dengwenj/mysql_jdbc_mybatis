### 什么是 JDBC
* JDBC(Java Database Connectivity) Java 连接数据库，可以使用 Java 语言连接数据库完成 CRUD 操作

### JDBC 核心思想
* Java 中定义了访问数据库的接口，可以为多种关系型数据库提供统一的访问方式。由数据库厂商提供驱动实现类（Driver 数据库驱动）

### JDBC API
* JDBC 是由多个接口和类进行功能实现
* class：java.sql.DriverManager 管理多个数据库驱动类，提供了获取数据库连接的方法
* interface：java.sql.Connection 代表一个数据库连接（当 connection 不是 null 时，表示已连接数据库）
* interface：java.sql.Statement 发送 SQL 语句到数据库工具
* interface：java.sql.ResultSet 保存 SQL 查询语句的结果数据（结果集）
* class：java.sql.SQLException 处理数据库应用程序时所发生的异常

### JDBC 开发步骤
### 注册驱动
* 使用 Class.forName("com.mysql.jdbc.Driver"); 手动加载字节码文件到 JVM 中

### 连接数据库
* 通过 DriverManager.getConnection(url, user, password) 获取数据库连接对象
* url：jdbc:mysql://localhost:3306/database
* username: root
* password: password
```java
public static void main(String[] args) throws ClassNotFoundException, SQLException {
    // 注册驱动
    Class.forName("com.mysql.jdbc.Driver");

    String url = "jdbc:mysql://localhost:3306/companydb";
    String username = "root";
    String password = "123456";
    // 连接数据库（java 提供的连接到数据库）
    Connection connection = DriverManager.getConnection(url, username, password);
    if (connection != null) {
        System.out.println("连接数据库成功");
    } else {
        System.out.println("连接数据库失败");
    }
}
```