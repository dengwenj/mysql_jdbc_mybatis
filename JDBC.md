### 什么是 JDBC
* JDBC(Java Database Connectivity) Java 连接数据库，可以使用 Java 语言连接数据库完成 CRUD 操作
* JDBC是一套接口（面向接口写实现类，能够解耦合，提高代码的扩展力）是由Java语言编写的一堆接口和一些class类组成的一套工具类的程序。
* 它仅仅是一套规范。也就是说JDBC并不能直接连接和操作数据库，真正连接和操作数据库的，是各大数据库服务商按照JDBC这套规范写出来的驱动Jar包，
* JDBC和数据库驱动包的关系，就是接口与实现类的关系

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

### 获取发送 SQL 的对象
* 通过 Connection 对象获得 Statement 对象，用于对数据库进行通用访问
* Statement statement = connection.createStatement();

### 执行 SQL 语句
* 执行 SQL 语句并接收执行结果
* String sql = "insert into t_jobs(JOB_ID, JOB_TITLE, MIN_SALARY, MAX_SALARY) values('H5', 'html', '6000', '10000')"
* int res = statement.executeUpdate(sql);
* 增删改都是用 executeUpdate() 方法
* 注意：在编写 DML 语句时，一定要注意字符串参数的符号是单引号 '值'
* DML 语句：增删改时，返回受影响行数（int 类型）
* DQL 语句：查询时，返回结果数据（ResultSet 结果集）

### 处理结果
* 接受处理操作的结果
* 受影响行数：逻辑判断、方法返回
* 查询结果集：迭代、依次获取

### 释放资源
* 遵循先开后关原则，释放所使用到的资源对象
```java
 public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // 1、注册驱动
        Class.forName("com.mysql.jdbc.Driver");

        String url = "jdbc:mysql://localhost:3306/companydb";
        String username = "pumu";
        String password = "123456";
        // 2、连接数据库（java 提供的连接到数据库）
        Connection connection = DriverManager.getConnection(url, username, password);
        if (connection != null) {
            System.out.println("连接数据库成功");

            // 3、获取发送 SQL 的对象
            Statement statement = connection.createStatement();

            // 4、编写 SQL  执行
            String sql = "insert into t_jobs(JOB_ID, JOB_TITLE, MIN_SALARY, MAX_SALARY) values('H5', 'html', '6000', '10000')";
            int res = statement.executeUpdate(sql);
            
            // 5. 处理结果
            if (res == 1) {
                System.out.println("添加成功");
            } else {
                System.out.println("添加失败");
            }
            
            // 6、释放资源
            statement.close();
            connection.close();
        } else {
            System.out.println("连接数据库失败");
        }
    }
```

### ResultSet(结果集)
* 在执行查询 SQL 后，存放查询到的结果集数据

### 接受结果集
* ResultSet rs = statement.executeQuery(sql);

### 遍历 ResultSet 中的数据
* ResultSet 以表（table）结构进行临时结果的存储，需要通过 JDBC API 将其中数据进行一次获取
* 每调用一次 next() 方法 ResultSet 的指针向下移动一个，结果为 true，表示当前行有数据
* rs.getXXX(整数)：代表根据列的编号顺序获取，从 1 开始
* rs.getXXX(列名)：代表根据列名获取
```java
public static void main(String[] args) throws Exception {
    // 1、注册驱动
    Class.forName("com.mysql.jdbc.Driver");
    // 2、获取连接
    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/companydb", "pumu", "123456");
    // 3、创建语句
    Statement statement = connection.createStatement();
    // 4、执行 SQL
    ResultSet resultSet = statement.executeQuery("select * from t_jobs");
    // 5、执行结果
    while (resultSet.next()) {
        System.out.println("通过编号找:");
        String jobId = resultSet.getString(1);
        String jobTitle = resultSet.getString(2);
        String minSalary = resultSet.getString(3);
        String maxSalary = resultSet.getString(4);
        System.out.println(jobId + ":" + jobTitle + ":" + minSalary + ":" + maxSalary);

        System.out.println("通过列名找:");
        String string = resultSet.getString("job_id");
        String string1 = resultSet.getString("job_title");
        String string2 = resultSet.getString("min_salary");
        String string3 = resultSet.getString("max_salary");
        System.out.println(string + ":" + string1 + ":" + string2 + ":" + string3);
        System.out.println("------------------------------------");
    }
    // 6、释放资源
    resultSet.close();
    statement.close();
    connection.close();
}
```

### SQL 注入问题
* 什么是 SQL 注入：用户输入的数据中有 SQL 关键字或语法并且参与了 SQL 语句的编译，导致 SQL 语句编译后的条件含义为 true，一直得到正确的结果，这种现象称为 SQL 注入

### 如何避免 SQL 注入
* 由于编写的 SQL 语句是在用户输入数据，整合后再进行编译。所以为了避免 SQL 注入的问题，我们要使 SQL 语句在用户输入数据前就已进行编译成完整的 SQL 语句，再进行填充数据

### PreparedStatement
* PreparedStatement 继承了 Statement 接口，执行 SQL 语句的方法无异
* 作用：1、预编译 SQL 语句，效率高
* 2、安全，避免 SQL 注入
* 3、可以动态的填充数据，执行多个同构的 SQL 语句

### 参数标记
* PreparedStatement pstmt = connt.prepareStatement("select * from user where username = ? and password = ?");
* 注意：JDBC 中的所有参数都由 ? 符号占位，这被称为参数标记。在执行 SQL 语句之前，必须为每个参数提供值
```java
public static void main(String[] args) throws Exception {
        // 通过控制台用户输入用户名和密码
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入用户名:");
        String username = scanner.next();
        System.out.println("请输入密码:");
        String password = scanner.next();

        // 注入驱动
        Class.forName("com.mysql.jdbc.Driver");
        // 获取连接
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/companydb", "pumu", "123456");
        // 获得 PreparedStatement 对象，预编译 SQL 语句
        PreparedStatement preparedStatement = connection.prepareStatement("select * from user where username=? and password=?");
        // ? 占位符
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);

        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            System.out.println("登录成功");
        } else {
            System.out.println("登录失败");
        }

        resultSet.close();
        preparedStatement.close();
        connection.close();
    }
```

### 封装工具类
* 在实际 JDBC 的使用中，存在着大量的重复代码，列如数据库的连接，关闭数据库等
* 我们需要把传统的 JDBC 代码进行重构，抽取出通用的 JDBC 工具类
```java
public class DBUtil {
    private static final Connection connection;

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/companydb", "pumu", "123456");
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() {
        return connection;
    }

    public static void close(ResultSet rs, Statement stmt, Connection conn) {
        try {
            if (rs != null) {
                rs.close();
            }

            if (stmt != null) {
                stmt.close();
            }

            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
```

### 封装工具类(配置文件的形式)
* 把可变的数据放入配置文件中，然后读取配置文件加载进来
```properties
driver=com.mysql.jdbc.Driver
url=jdbc:mysql://localhost:3306/companydb
user=pumu
password=123456
```

### ORM
* ORM(Object Relational Mapping) 对象关系映射
* 从数据库查询到的结果集(ResultSet)在进行遍历时，逐行遍历，取出的都是零散的数据。在实际应用开发中，我们需要将零散的数据进行封装整理

### 实体类（entity）：零散数据的载体
* 一行数据中，多个零散的数据进行整理
* 通过 entity 的规则对表中的数据进行对象的封装
* 表名=类名，列名=属性名，提供各个属性的 get、set 方法
* 提供无参构造方法

### DAO 数据访问对象（Data Access Object）
* DAO 实现了业务逻辑与数据库访问相分离
* 对同一张表的所有操作封装在 XxxDaoImpl 对象中
* 根据增删改查的不同功能实现具体的方法（insert、update、delete、select、selectAll）

### Date 工具类
* 数据库存储的数据类型为 java.sql.Date，而 java 应用层存储日期类型为 java.util.Date。需要进行转换

### java.util.Date
* Java 语言常规应用层面的日期类型，可以通过字符串创建对应的时间对象
* 无法直接通过 JDBC 插入到数据库

### java.sql.Date
* 不可以通过字符串创建对应的时间对象，只能通过毫秒值创建对象
* 可以直接通过 JDBC 插入到数据库

### SimpleDateFormat
* 格式化和解析日期的具体类。允许进行格式化（日期 -> 文本）、解析（文本 -> 日期）和规范化
```java
public static void main(String[] args) throws Exception {
        String date = "2024-11-05";

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        // 转换成 util data
        Date parse = simpleDateFormat.parse(date);
        System.out.println("parse -> " + parse); // parse -> Tue Nov 05 00:00:00 CST 2024

        String format = simpleDateFormat.format(parse);
        System.out.println("format -> " + format); // 2024-11-05

        // 转换成 sql date
        java.sql.Date sqlDate = new java.sql.Date(parse.getTime());
        System.out.println(sqlDate);
    }
```

### Service 业务逻辑层
* 什么是业务：代表用户完成的一个业务功能，可以由一个或多个 DAO 的调用组层
```java
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
```

### 当转账程序出现异常，事务控制成功了吗?
* 解决方案1：传递 Connection
* 为了解决线程中 Connection 对象不同步的问题，可以将 Connection 对象通过 service 传递给各个 DAO 方法
* 传递的问题：
* 1、如果使用传递 Connection，容易造成接口污染
* 2、定义接口是为了更容易更换实现，而将 Connection 定义在接口中，会造成污染当前接口

### 解决方案2：ThreadLocal
* 可以将整个线程中（单线程）中，存储一个共享值。一个线程中可以存储多个ThreadLocal，存在ThreadLocalMap中
* 线程拥有一个类似 Map 的属性，键值对结构 《ThreadLocal 对象, 值》
* ThreadLocal 应用：一个线程共享同一个 threadLocal，在整个流程中任一环节可以存值或取值
```java
private static final ThreadLocal<Connection> threadLocal = new ThreadLocal<>();
public static Connection getConnection() throws SQLException {
        // 将当前线程中绑定的 Connection 对象赋值给 connection
        Connection connection = threadLocal.get();

        String url = properties.getProperty("url");
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        if (connection == null) {
            connection = DriverManager.getConnection(url, user, password);
            // 把连接存在当前线程共享中
            threadLocal.set(connection);
        }
        return connection;
    }
```

### 三层架构
* 1、表示层
* 命名：XXXView。职责：收集用户的数据和需求、展示数据
* 2、业务逻辑层
* 命名：XXXServiceImpl。职责：数据加工处理、调用 DAO 完成业务实现、控制事务
* 3、数据访问层
* 命名：XXXDaoImpl。职责：向业务层提供数据，将业务层加工后的数据同步到数据库

### Druid 连接池
* 在程序初始化时，预先创建指定数量的数据库连接对象存储在池中，当需要连接数据库时，从连接池中取出现有连接，
* 使用完毕后，也不会进行关闭，而是放回池中，实现复用，节省资源
```java
public class DBUtil {
    // 声明连接池对象
    private static final DruidDataSource dataSource;

    static {
        Properties prop = new Properties();
        InputStream is = DBUtil.class.getResourceAsStream("/database.properties");
        try {
            prop.load(is);
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(prop);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // 获取连接
    public static Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
```

### Apache 的 DbUtils 使用
* Commons DbUtils 是 Apache 组织提供的一个对 JDBC 进行简单封装的开源工具类库，使用它能够简化 JDBC 应用程序的开发

### DbUtils 简介
* DbUtils 是 java 变成中数据库操作实用小工具，小巧，简单，实用
* 对于数据库的查询操作，可以把结果转换为 List、Array、Set等集合，便于操作
* 对于数据库的 DML 操作，也变得很简单（只需要写 SQL 语句）

### DbUtils 主要包含
* ResultSetHandler 接口：转换类型接口
* BeanHandler 类：实现类，把一条记录转换成对象
* BeanListHandler 类：实现类，把多条记录转换成 List 集合
* ScalarHandler 类：实现类，适合获取一行一列的数据
* QueryRunner：执行 sql 语句的类
* 增删改：update()
* 查询：query()