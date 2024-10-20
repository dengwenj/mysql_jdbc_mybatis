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
```mysql
create database mydb1; # 创建 mydb 数据库
create database mydb2 character set gbk; # 创建数据库并设置编码格式为 gbk，如果不设置编码格式按照电脑默认的编码格式
create database if not exists mydb2; # 如果 mydb2 数据库不存在，则创建，反之不创建
```

### 查看数据库创建信息
```mysql
show create database mydb2; # 查看创建数据库是的基本信息
```

### 修改数据库
```mysql
alter database mydb2 character set utf8;
```

### 删除数据库
```mysql
drop database mydb2;
```

### 查看当前所有用的数据库
```mysql
select database();
```

### 使用数据库
```mysql
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

### 列的别名c
* 列 as '列名'
* select first_name as '姓名' from t_employees;

### 查询结果去重
* distinct 列名
* select distinct manager_id from t_employees;