### 数据库系列

### 概念
* 数据库是：按照数据结构来组织、存储和管理数据的仓库。是一个长期存储在计算机内的，有组织的、有共享的、统一管理的数据集合

### 数据库的分类
* 网状结构数据库：以节点形式存储和访问
* 层次结构数据库：定向有序的树状结构实现存储和访问
* 关系结构数据库：Oracle、DB2、Mysql、SQL Server，以表格（table）存储，多表间建立关联关系，通过分类、合并、连接、选取等运算实现访问
* 非关系型数据库：MongoDB、Redis，多数使用哈希表，表中以键值（key-value）的方式实现特定的键和一个指针指向的特定数据

### 数据库管理系统
* 指一种操作和管理数据库的大型软件，用于建立、使用和维护数据库，对数据库进行统一管理和控制，以保证数据库的安全性和完整性，用户通过数据库管理系统访问数据库中的数据

### 创建数据库管理系统
* Oracle、DB2、SQL Server、SQLLite、MySQL

### SQL 语言
* SQL 结构化查询语言，用于存取数据、更新、查询和管理关系数据库系统的程序设计语言
* 通常执行对数据库的增删改查，C（Create）R（Read）U（Update）D（Delete）
* MySQL 应用：对于数据库的操作，需要在进入 MySQL 环境下进行指令输入，并在一句指令的末尾使用 ; 结束

### 基本命令
* 查看 MySQL 中所有数据库
* SHOW DATABASES; # 显示当前 MySQL 中包含的所有数据库。MySQL 系统默认有如下数据库：
* information_schema：信息数据库，其中保存着关于所有数据库的信息（元数据）。元数据是关于数据的数据，如数据库名或表名，列的数据类型，或访问权限等
* mysql：核心数据库，主要负责存储数据库的用户、权限设置、关键字等。以及需要使用的控制和管理信息，不可以删除
* performance_schema：性能优化的数据库，MySQL5.5 版本中新增的一个性能优化的引擎
* sys：系统数据库，MySQL5.7 版本中新增的可以快速的了解元数据信息的系统库，便于发现数据库的多样信息，解决性能瓶颈问题

### 创建自定义数据库
```text
create database mydb1; # 创建 mydb 数据库
create database mydb2 character set gbk; # 创建数据库并设置编码格式为 gbk，如果不设置编码格式按照电脑默认的编码格式
create database if not exists mydb2; # 如果 mydb2 数据库不存在，则创建，反之不创建
```

### 查看数据库创建信息
```text
show create database mydb2; # 查看创建数据库是的基本信息
```

### 修改数据库
```text
alter database mydb2 character set utf8;
```

### 删除数据库
```text
drop database mydb2;
```

### 查看当前所有用的数据库
```text
select database();
```

### 使用数据库
```text
use mydb2;
```

### 数据查询
* 数据库表的基本结构：关系结构数据库是以表格（table）进行数据存储，表格由“行”和“列”组成
* 执行查询语句返回的结果集是一张虚拟表

### 基本查询
* select 列名 from 表名;
* select：指定要查询的列
* from：指定要查询的表

### 查询部分列
* select employee_id, first_name from t_employees;

### 查询所有列
* select 所有列的列名 from t_employees;
* select * from t_employees;
* 生产环境下，优先使用列名查询。*的方式需转换成全列名，效率低，可读性差

### 对列中的数据进行运算
* select employee_id, first_name, salary*12 from t_employees; 
* 可进行加减乘除 + - * /
* 注意：% 是占位符，而非模运算符

### 列的别名
* 列 as '列名'
* select first_name as '姓名' from t_employees;

### 查询结果去重
* distinct 列名
* select distinct manager_id from t_employees;

### 排序查询
* select 列名 from 表名 order by 排序列 【排序规则】
* ASC：对前面排序列做升序排序
* DESC：对前面排序列做降序排序

### 依据单列排序
* select employee_id, salary from t_employees order by salary desc; # 根据薪资从降序排序

### 依据多列排序
* select employee_id, salary from t_employees order by salary desc, employee_id asc;
* order by salary desc, employee_id asc：前面的列有相同的值了，在按照后面的排序规则进行排序

### 条件查询
* select 列名 from 表名 where 条件
* where 条件：在查询结果中，筛选符合条件的查询结果，条件为布尔表达式

### 等值判断（=）
* select salary from t_employees where salary = 11000;

### 逻辑判断（and、or、not）
* select salary from t_employees where salary = 11000 and commission_pct = 0.30;
* select salary from t_employees where salary = 11000 or commission_pct = 0.30;
* select salary from t_employees where not salary = 11000;

### 不等值判断（》、《、 》=、《=、!=、《》）
* select salary from t_employees salary >= 6000 and salary <= 10000;

### 区间判断（between and）
* select * from t_employees where salary between 6000 and 10000;
* 注意：在区间判断语法中，小值在前，大值在后，反之，得不到正确结果

### NULL 值判断（IS NULL、IS NOT NULL）
* select salary from t_employees where commission_pct is null
* select salary from t_employees where commission_pct is not null

### 枚举查询（IN(值1, 值2, 值3)）
* select salary from t_employees where department_id in(70, 80, 90)
* 注意：in 的查询效率较低，可通过多条件拼接

### 模糊查询
* LIKE_（**单个**任意字符）
* LIKE%（**任意长度**的任意字符）
* 注意：模糊查询只能和 LIKE 关键字结合使用
* select salary from t_employees where first_name LIKE 'L%'; 开头为 L 的任意长度任意字符
* select salary from t_employees where first_name LIKE 'L_'; 开头为 L 的单个长度任意字符
* select salary from t_employees where first_name LIKE '%L%'; 只要有 L 就可以

### 分支结构查询
```text
SELECT EMPLOYEE_ID, FIRST_NAME, SALARY,
       CASE
           WHEN SALARY >= 10000 THEN 'A'
           WHEN SALARY >= 6000 AND SALARY < 10000 THEN 'B'
           ELSE 'C'
       END AS '薪资等级'
FROM t_employees;
```
* 注意：通过使用 CASE END 进行条件判断，每条数据对应生成一个值
* 类似 java 中 switch
* case end 会产生一个结果，会生成一个独立的列

### 任何一个函数都是可以对表进行应用操作，可以生成独立的一个列
### 时间查询
* 语法：select 事件函数(参数列表)
```text
-- 获取当前系统时间
SELECT SYSDATE();
-- 获取当前日期
SELECT CURDATE();
-- 获取当前时间
SELECT CURTIME();
-- 获取指定日期为一年中的第几周
SELECT WEEK('2024-10-26'); # 42 周
-- 获取指定日期的年份
SELECT YEAR(SYSDATE());
-- 获取指定时间的小时值
SELECT HOUR(CURTIME());
-- 获取指定日期的分钟值
SELECT MINUTE(CURTIME());
-- 获取 date1 和 date2 之间相隔的天数
SELECT DATEDIFF('2024-10-26', '2024-05-10'); # 169 天
-- 计算 date 加上 n 天后的日期 
SELECT ADDDATE('2024-10-26','40'); # 2024-12-05
```

### 字符串查询 
* 语法：SELECT 字符串函数(参数列表)
```text
-- 将多个字符串拼接
SELECT CONCAT('你好','世界','哈哈'); # 你好世界哈哈
SELECT CONCAT(FIRST_NAME,LAST_NAME) as '姓名' FROM t_employees;
-- INSERT(str,pos,len,newStr) 将 str 中指定 pos 位置开始 len 长度的内容替换成 newStr (替换)
SELECT INSERT('这是一个数据库', 3, 2, 'mysql') # 这是mysql数据库
SELECT LOWER('MYSQL'); # mysql
SELECT UPPER('mysql'); # MYSQL
-- SUBSTRING(str, num, len) 将 str 字符串指定 num 位置开始截取 len 个内容 (截取)
SELECT SUBSTRING('你好啊朴睦', 4, 2) # 朴睦
```

### 聚合函数（对列进行操作的)
* 语法：select 聚合函数(列名) from 表名;
* 对多条数据的**单列**进行统计，返回统计后的一行结果
* SUM()：求所有行中单列结果的总和
* AVG()：平均值
* MAX()：最大值
* MIN()：最小值
* COUNT()：求总行数。分组了话就是查询的每组的总数
* 聚合函数自动忽略 null 值，不进行统计
* 默认聚合列不能和非聚合列查询，要想查询加上分组 group by
```text
SELECT SUM(SALARY) FROM t_employees;
SELECT AVG(SALARY) FROM t_employees;
SELECT MAX(SALARY + 0) FROM t_employees;
SELECT MIN(SALARY + 0) FROM t_employees;
SELECT COUNT(EMPLOYEE_ID) FROM t_employees;
-- 统计有提成的人数 聚合函数自动忽略 null 值，不进行统计
SELECT COUNT(COMMISSION_PCT) FROM t_employees;
```

### 分组查询（分组一般和聚合函数一起使用）
* 语法：select 列名 from 表名 where 条件 group by 分组依据(列);
* 关键字：group by  说明：分组依据，必须在 where 之后生效
* 常见问题：分组查询中，select 显示的列只能是分组依据列，或者聚合函数列，不能出现其他列，不然会出现问题
```text
-- 分组查询
# 查询各部门的总人数
SELECT DEPARTMENT_ID, COUNT(EMPLOYEE_ID) 
FROM t_employees 
GROUP BY DEPARTMENT_ID;

# 查询各个部门，各个岗位的人数
SELECT DEPARTMENT_ID, JOB_ID, COUNT(*)
FROM t_employees
GROUP BY DEPARTMENT_ID, JOB_ID;
```

### 分组过滤查询
* 语法：SELECT 列名 from 表名 where 条件 group by 分组列 HAVING 过滤规则
```text
# 统计部门的最高工资 （统计 60、70、90 号部门的最高工资）
SELECT DEPARTMENT_ID, MAX(SALARY)
FROM t_employees
GROUP BY DEPARTMENT_ID
HAVING DEPARTMENT_ID IN(60, 70, 90);
```

### 限定查询
* SELECT 列名 FROM 表名 LIMIT 起始行, 查询行数;
* LIMIT offset_start, row_count 限定查询结果的起始行和总行数
```text
# 限定查询，从零开始的
SELECT * FROM t_employees LIMIT 0, 5;
SELECT * FROM t_employees LIMIT 3, 10;
```
* 在分页应用场景中，起始行是变化的，但是一页显示的条数是不变的

### 查询总结
* SQL 语句编写顺序：SELECT 列名 表名 WHERE 条件 GROUP BY 分组依据(列) HAVING 过滤条件 ORDER BY 排序列 asc|desc LIMIT 起始行, 总条数;

### SQL 语句执行顺序
* FROM：指定数据来源表
* WHERE：对查询数据做第一次过滤
* GROUP BY：分组
* HAVING：对分组后的数据第二次过滤
* SELECT：查询各字段的值
* ORDER BY：排序
* LIMIT：限定查询结果

### 子查询（作为条件判断）
* SELECT 列名 FROM 表名 WHERE 条件（子查询结果）
* 注意: 将子查询 ”一行一列“ 的结果作为外部查询的条件，做第二次查询
* 子查询得到一行一列的结果才能作为外部查询的等值判断条件或不等值条件判断
```text
-- 查询工资大于 Bruce 的员工信息
SELECT * FROM t_employees WHERE SALARY > (SELECT SALARY FROM t_employees WHERE FIRST_NAME = 'Bruce')
```

### 子查询（作为枚举查询条件）
* SELECT 列名 FROM 表名 WHERE 列名 in(子查询结果)
* 将子查询 ”多行一列“ 的结果作为外部查询的枚举查询条件，做第二次查询
```text
-- 查询与名为 King 同一部门的员工信息
SELECT * FROM t_employees WHERE DEPARTMENT_ID IN(
	SELECT DEPARTMENT_ID FROM t_employees WHERE LAST_NAME = 'King'
);
```
* 当子查询结果集形式为多行单列时可以使用 ANY(任意) 或 ALL(全部) 关键字
```text
-- 工资高于 60部门所有人的信息
SELECT * FROM t_employees WHERE SALARY > ALL(SELECT SALARY FROM t_employees WHERE DEPARTMENT_ID = '60')
SELECT * FROM t_employees WHERE SALARY > ANY(SELECT SALARY FROM t_employees WHERE DEPARTMENT_ID = '60')
```

### 子查询（作为一张表）
* SELECT 列名 FROM （子查询的结果集）WHERE 条件;
```text
SELECT FIRST_NAME FROM (
	SELECT * FROM t_employees ORDER BY SALARY DESC
) AS temp LIMIT 0,5
```
* 将子查询 "多行多列" 的结果作为外部查询的一张表，做第二次查询
* 注意：子查询作为临时表，为其赋予一个临时表名

### 合并查询（结果集作为合并）
* SELECT * FROM 表名1 UNION SELECT * FROM 表名2;
* SELECT * FROM 表名1 UNION ALL SELECT * FROM 表名2;
* 合并结果的两张表，列数必须相同，列的数据类型可以不同
* UNION 去除重复项(全部重复)，UNION ALL 不去除重复项
```text
SELECT * FROM t_departments UNION SELECT * FROM t_jobs;
SELECT * FROM t_departments UNION ALL SELECT * FROM t_jobs;
```

### 表连接查询（多表查询）
* SELECT 列名 FROM 表1 连接方式 表2 ON 连接条件
* 如果不指定连接条件，则会造成笛卡尔积的结果

### 内连接查询（INNER JOIN   ON）
* 返回两个或多个表中有匹配的记录。只有在两个表中都有匹配的行才会出现在结果集中。
```text
# 方式一
SELECT EMPLOYEE_ID, t_employees.JOB_ID, t_jobs.JOB_TITLE FROM t_employees 
INNER JOIN t_jobs 
ON t_employees.JOB_ID = t_jobs.JOB_ID
# 方式二
SELECT * FROM t_employees, t_jobs WHERE t_employees.JOB_ID = t_jobs.JOB_ID;
```
* 在 MySQL 中，第二种方式也可以作为内连接查询，但不符合 SQL 标准
* 而第一种属于 SQL 标准，与其他关系型数据库通用
```text
-- 三表查询： 查询所有员工工号，名字，部门名称，部门所在国家ID
SELECT * FROM t_employees
INNER JOIN t_departments ON t_employees.DEPARTMENT_ID = t_departments.DEPARTMENT_ID
INNER JOIN t_locations ON t_departments.LOCATION_ID = t_locations.LOCATION_ID;
```

### 左外连接（left join on）
* 左外连接，是以左表为主表，依次向右匹配，匹配到，返回结果，匹配不到，返回 NULL 值填充
```text
SELECT * FROM t_employees
LEFT JOIN t_departments ON t_employees.DEPARTMENT_ID = t_departments.DEPARTMENT_ID;
```

### 右外连接（right join on）
* 右外连接，是以右表为主表，依次向左匹配，匹配到，返回结果，匹配不到，则返回 NULL 值填充
```text
SELECT * FROM t_employees
RIGHT JOIN t_departments ON t_employees.DEPARTMENT_ID = t_departments.DEPARTMENT_ID;
```

### 内连接、外连接
* 内连接：只选择两个表中有匹配的记录
* 左外连接：选择左表的所有记录，即使右表中没有匹配，右表中没有匹配到，不会出现在查询的表中
* 右外连接：选择右表的所有记录，即使左表中没有匹配，左表中没有匹配到，不会出现在查询的表中

### 新增（INSERT）
* INSERT INTO 表名 (列1, 列2, 列3...) VALUES (值1, 值2, 值3);
* 注意：表名后的列名和 VALUES 里的值要一一对应（个数、顺序、类型）
```text
INSERT INTO t_jobs (JOB_ID, JOB_TITLE, MIN_SALARY, MAX_SALARY) VALUES ('go', 'golang', 10000, 50000);

-- INSERT INTO 表名 (列...) VALUES (值...)
```

### 修改（UPDATE）
* UPDATE 表名 SET 列1=新值, 列2=新值,... WHERE 条件;
* 注意：SET 后多个列名=值，绝大多数情况下都要加 WHERE 条件，指定修改，否则为整表更新
```text
UPDATE t_employees SET SALARY = '25000' WHERE EMPLOYEE_ID = '100';
UPDATE t_employees SET JOB_ID = 'AD_VP', SALARY = '30000' WHERE EMPLOYEE_ID = '103';
```

### 删除（DELETE）
* DELETE FROM 表名 WHERE 条件;
* 注意：删除时，若不加 WHERE 条件，，删除的是整张表的数据
```text
DELETE FROM t_employees WHERE EMPLOYEE_ID = '107';
DELETE FROM t_employees WHERE FIRST_NAME = 'Peter' AND LAST_NAME = 'Hall';
```

### 清空整表数据（TRUNCATE）
* TRUNCATE TABLE 表名;
* 注意：与 DELETE 不加 WHERE 删除整表数据不同，TRUNCATE 是把表销毁，再按照原表的格式创建一张新表

### 数据表操作
### 数据类型
* MySQL 支持多种类型，大致可以分为三类：数值、日期/时间和字符串(字符)类型，对于我们约束数据的类型有很大的帮助

### 数值类型
* INT：4字节，大整数值
* DOUBLE：8字节，双精度浮点数值
* DOUBLE(M,D)：8个字节，M表示长度，D表示小数位数，DOUBLE(5, 2) -999.99 ~ 999.99，双精度浮点数值
* decimal(M,D)：decimal(5, 2) -999.99 ~ 999.99，小数值

### 日期类型
* DATE：格式YYYY-MM-DD 日期值
* TIME：HH:MM:SS 时间值或持续时间
* YEAR：YYYY 混合日期和时间值
* DATETIME：YYYY-MM-DD HH:MM:SS 混合日期和时间值
* TIMESTAMP：YYYYMMDDHHMMSS 混合日期和时间值，时间戳

### 字符串类型
* CHAR：0-255字符，定长字符串 char(10) 10个字符
* VARCHAR：0-65535 字符，变长字符串 varchar(10) 10个字符
* BLOB(binary large object)：0-65535 字节，二进制形式的长文本数据
* TEXT：0-65535 字符，长文本数据
* CHAR 和 VARCHAR 类型类似，但它们保存和检索的方式不同。它们的最大长度和 是否尾部空格被保留等方面也不同。在存储或检索过程中不进行大小写转换
* BLOB 是一个二进制大对象，可以容纳可变数据的数据。有四种 BLOB 类型：TINYBLOB、BLOB、MEDIUMBLOB 和 LONGBLOB。它们只是可容纳值的最大长度不同

### 数据表的创建（CREATE）
```text
CREATE TABLE 表名 (
    列名 数据类型【约束】,
    列名 数据类型【约束】,
    ...
    列名 数据类型【约束】 最后一列的末尾不加逗号
)【charset=utf8】
```

###  
* alter table 表名 操作;

### 向现有表中添加列
* alter table 表名 add 列名 数据类型...;

### 修改表中的列
* alter table 表名 modify subjectName varchar(10);
* 注意：修改表中的某列时，也要写全列的名字，数据类型，约束

### 删除表中的列
* alter table 表名 drop subjectName;
* 注意：删除列时，每次只能删除一列

### 修改列名
* alter table 表名 change subjectHours classHours int;
* 注意：修改列名时，在给定列新名称时，要指定列的类型和约束
* modify 只是修复列的数据类型或约束，change 修改的是列名

### 
* alter table 表名 rename sub;

### 数据表的删除（DROP）
* drop table 表名
```text
# 向现有表添加列
alter table `subject` add pumu VARCHAR(10);
-- 修改表中的列
alter table `subject` modify subjectName VARCHAR(10);
-- 删除表中的列
ALTER TABLE `subject` DROP pumu;
-- 修改列名
ALTER TABLE `subject` CHANGE subjectHours classHours INT;
-- 修改表名
ALTER TABLE `subject` RENAME sub;
-- 删除表 
DROP TABLE sub;
```

### 实体完整性约束(一行的数据是唯一的)
* 表中的一行数据代表一个实体（entity），实体完整性的作用即是标识每一行数据不重复、实体唯一

### 主键约束
* PRIMARY KEY 唯一，标识表中的一行数据，此列的值不可重复，且不能为 NULL

### 唯一约束
* UNIQUE 唯一，标识表中的一行数据，不可重复，可以为 NULL，unique 可以用在多个列上

### 自动增长列
* AUTO_INCREMENT 自动增长，给主键数值列添加自动增长，从1开始，每次加1，不能单独使用，和主键配合
```text
CREATE TABLE `subject`(
	subjectId INT PRIMARY KEY AUTO_INCREMENT,
	subjectName VARCHAR(10) UNIQUE,
	subjectHours INT
)
SELECT * FROM `subject`;
INSERT INTO `subject`(subjectName, subjectHours) VALUES('Java', 20);
INSERT INTO `subject`(subjectName, subjectHours) VALUES('JS', 20);
```