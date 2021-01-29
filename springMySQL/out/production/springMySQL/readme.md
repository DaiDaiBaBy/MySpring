# Spring JDBC
在使用普通的 JDBC 数据库时，就会很麻烦的写不必要的代码来处理异常，打开和关闭数据库连接等
但 Spring JDBC 框架负责所有的低层细节，从开始打开连接，准备和执行 SQL 语句，处理异常，处理事务，到最后关闭连接
所以当从数据库中获取数据时，你所做的是定义连接参数，指定要执行的 SQL 语句，每次迭代完成所需的工作

## jdbcTemplate类
JdbcTemplate 类执行 SQL 查询、更新语句和存储过程调用，执行迭代结果集和提取返回参数值
JdbcTemplate 类的实例是线程安全配置的
使用 JdbcTemplate 类时常见的做法是在你的 Spring 配置文件中配置数据源，
然后共享数据源 bean 依赖注入到 DAO 类中，并在数据源的设值函数中创建了 JdbcTemplate

## 配置数据源(XML)
这里提供一个数据源到 JdbcTemplate 中，所以它可以配置本身来获得数据库访问
```xml
<bean id="dataSource"
class="org.springframework.jdbc.datasource.DriverManagerDataSource">
   <property name="driverClassName" value="com.mysql.jdbc.Driver"/>
   <property name="url" value="jdbc:mysql://localhost:3306/TEST"/>
   <property name="username" value="root"/>
   <property name="password" value="password"/>
</bean>
```

## Spring 中SQL的存储过程
SimpleJdbcCall 类可以被用于调用一个包含 IN 和 OUT 参数的存储过程
什么是存储过程
　　存储过程，带有逻辑的sql语句
存储过程特点
　　1）执行效率非常快！存储过程是在数据库的服务器端执行
　　2）移植性很差！不同的数据库的存储过程是不能移植的
```text
创建存储过程:
DELIMITER $     --声明存储过程的结束符
DROP PROCEDURE IF EXISTS stu_test $
CREATE PROCEDURE stu_test()    --存储过程名称（参数列表）
BEGIN    --开始
    -- 可以写多个sql语句           -- sql语句+流程控制    
END $     --结束 结束符

-- 执行存储过程
call stu_test()   --call 存储过程名称（参数）
```

这里创建一个sql储存过程：数据库名 TEST 表Student
DELIMITER $$ 
DROP PROCEDURE IF EXISTS 'TEST'.'getRecord' $$
CREATE PROCEDURE 'TEST'.'getRecord'(
    IN in_id INTEGER,
    OUT out_name  VARCHAR(20),
    OUT out_age  INTEGER
)
BEGIN
    SELECT name, age INTO out_name, out_age
    FROM Student where id = in_id;
END $$

参数：
IN:  表示输入参数，可以携带数据带存储过程中
OUT: 表示输出参数，可以从存储过程中返回结果 
INOUT: 表示输入输出参数，两者结合
储存过程使用取值：
```text
SqlParameterSource in = new MapSqlParameterSource().addValue("in_id", id);
Map<String, Object> out = simpleJdbcCall.execute(in);

MapSqlParameterSource extends abstractMapSqlParameterSource
abstractMapSqlParameterSource implements SqlParameterSource
```


# Spring 事务管理
一个数据库事务是一个被视为单一的工作单元的操作序列。这些操作应该要么完整地执行，要么完全不执行(确保数据完整性和一致性)
主要分为四属性：ACID
原子性： 事务应该当作一个单独单元的操作，这意味着整个序列操作要么是成功，要么是失败的  
一致性： 这表示数据库的引用完整性的一致性，表中唯一的主键等  
隔离性： 可能同时处理很多有相同的数据集的事务，每个事务应该与其他事务隔离，以防止数据损坏
持久性： 一个事务一旦完成全部操作后，这个事务的结果必须是永久性的，不能因系统故障而从数据库中删除

一个真正的 RDBMS（关系型） 数据库系统将为每个事务保证所有的四个属性。使用 SQL 发布到数据库中的事务的简单视图如下：
使用 begin transaction 命令开始事务
使用 SQL 查询语句执行各种删除、更新或插入操作
如果所有的操作都成功，则执行提交操作，否则回滚所有操作

局部事务 Vs 全局事务
局部事务是特定于一个单一的事务资源，如一个 JDBC 连接，而全局事务可以跨多个事务资源事务，如在一个分布式系统中的事务

编程式事务 Vs 声明式事务
Spring 支持两种类型的事务管理:
    编程式事务管理：这意味着你在编程的帮助下有管理事务。这给了你极大的灵活性，但却很难维护
    声明式事务管理：这意味着你从业务代码中分离事务管理。你仅仅使用注释或 XML 配置来管理事务
声明式事务管理比编程式事务管理更可取，尽管它不如编程式事务管理灵活，但它允许你通过代码控制事务
但作为一种横切关注点，声明式事务管理可以使用 AOP 方法进行模块化。Spring 支持使用 Spring AOP 框架的声明式事务管理


1.  Spring事务抽象：
Spring事务管理的五大属性：隔离级别、传播行为、是否只读、事务超时、回滚规则
Spring 事务抽象的关键是由 org.springframework.transaction.PlatformTransactionManager 接口定义，如下所示：
```java
public interface PlatformTransactionManager {
   TransactionStatus getTransaction(TransactionDefinition definition) throws TransactionException;
   void commit(TransactionStatus status) throws TransactionException;
   void rollback(TransactionStatus status) throws TransactionException;
}
```
TransactionStatus getTransaction(TransactionDefinition definition)
根据指定的传播行为，该方法返回当前活动事务或创建一个新的事务
void commit(TransactionStatus status)  该方法提交给定的事务和关于它的状态
void rollback(TransactionStatus status)  该方法执行一个给定事务的回滚


2.  TransactionDefinition 是在 Spring 中事务支持的核心接口，它的定义如下：
```java
public interface TransactionDefinition {
   int getPropagationBehavior();
   int getIsolationLevel();
   String getName();
   int getTimeout();
   boolean isReadOnly();
}
```
int getPropagationBehavior()  该方法返回传播行为。Spring 提供了与 EJB CMT 类似的所有的事务传播选项
int getIsolationLevel()  该方法返回该事务独立于其他事务的工作的程度
String getName()    该方法返回该事务的名称
int getTimeout()    该方法返回以秒为单位的时间间隔，事务必须在该时间间隔内完成
boolean isReadOnly()    该方法返回该事务是否是只读的

3.  事务隔离级别值：
|隔离|描述|
TransactionDefinition.ISOLATION_DEFAULT         默认级别
TransactionDefinition.ISOLATION_READ_COMMITTED  表明能够阻止误读；可以发生不可重复读和虚读
TransactionDefinition.ISOLATION_READ_UNCOMMITTED    表明可以发生误读、不可重复读和虚读
TransactionDefinition.ISOLATION_REPEATABLE_READ     表明能够阻止误读和不可重复读；可以发生虚读
TransactionDefinition.ISOLATION_SERIALIZABLE        表明能够阻止误读、不可重复读和虚读

4.  事务传播行为：
|传播行为|描述|
TransactionDefinition.PROPAGATION_MANDATORY    支持当前事务；如果不存在当前事务，则抛出一个异常
TransactionDefinition.PROPAGATION_NESTED        如果存在当前事务，则在一个嵌套的事务中执行
TransactionDefinition.PROPAGATION_NEVER         不支持当前事务；如果存在当前事务，则抛出一个异常
TransactionDefinition.PROPAGATION_NOT_SUPPORTED     不支持当前事务；而总是执行非事务性
TransactionDefinition.PROPAGATION_REQUIRED      支持当前事务；如果不存在事务，则创建一个新的事务
TransactionDefinition.PROPAGATION_REQUIRES_NEW     创建一个新事务，如果存在一个事务，则把当前事务挂起
TransactionDefinition.PROPAGATION_SUPPORTS      支持当前事务；如果不存在，则执行非事务性
TransactionDefinition.TIMEOUT_DEFAULT           使用默认超时的底层事务系统，或者如果不支持超时则没有

5.  TransactionStatus 接口为事务代码提供了一个简单的方法来控制事务的执行和查询事务状态：
```java
public interface TransactionStatus extends SavepointManager {
   boolean isNewTransaction();
   boolean hasSavepoint();
   void setRollbackOnly();
   boolean isRollbackOnly();
   boolean isCompleted();
}
```
|方法|描述|
boolean hasSavepoint()  该方法返回该事务内部是否有一个保存点，也就是说，基于一个保存点已经创建了嵌套事务
boolean isCompleted()   该方法返回该事务是否完成，也就是说，它是否已经提交或回滚
boolean isNewTransaction()  在当前事务时新的情况下，该方法返回 true
boolean isRollbackOnly()    该方法返回该事务是否已标记为 rollback-only
void setRollbackOnly()      该方法设置该事务为 rollback-only 标记

## Spring 编程式事务的管理
这里我们使用：
    PlatformTransactionManager 来实现编程式方法从而实现事务
    要开始一个新事务，则需要有一个带有适当的 transaction 属性的 TransactionDefinition 的实例
    使用默认的 transaction 属性简单的创建了 DefaultTransactionDefinition 的一个实例
    当 TransactionDefinition 创建后，通过调用 getTransaction() 方法来开始你的事务，该方法会返回 TransactionStatus 的一个实例
    TransactionStatus 对象帮助追踪当前的事务状态，并且最终，如果一切运行顺利，你可以使用 PlatformTransactionManager 的 commit() 方法来提交这个事务
        否则的话，你可以使用 rollback() 方法来回滚整个操作
```text
private PlatformTransactionManager transactionManager;
public void setTransactionManager(
      PlatformTransactionManager transactionr) {
      this.transactionManager = transactionr;
   }
TransactionDefinition def = new DefaultTransactionDefinition();
// 开始事务  返回一个status对象  可以跟踪事务的状态  及时提交 或者回滚等操作
TransactionStatus status = transactionManager.getTransaction(def);
try{
///////
transactionManager.commit(status);
} catch{
transactionManager.rollback(status);
}
```

## Spring 声明式事务的管理
声明式即为：使用注释或基于配置的 XML 来管理事务
步骤：
使用标签，创建一个事务处理的建议，同时，定义一个匹配所有方法的切入点，我们希望这些方法是事务型的并且会引用事务型的建议
如果在事务型配置中包含了一个方法的名称，那么创建的建议在调用方法之前就会在事务中开始进行
目标方法会在 try / catch 块中执行
如果方法正常结束，AOP 建议会成功的提交事务，否则它执行回滚操作

    
    







