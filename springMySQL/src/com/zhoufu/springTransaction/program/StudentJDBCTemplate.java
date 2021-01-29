package com.zhoufu.springTransaction.program;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;
import java.util.List;

public class StudentJDBCTemplate implements StudentDAO {
   private DataSource dataSource;
   private JdbcTemplate jdbcTemplateObject;
   private PlatformTransactionManager transactionManager;
   @Override
   public void setDataSource(DataSource dataSource) {
      this.dataSource = dataSource;
      this.jdbcTemplateObject = new JdbcTemplate(dataSource);
   }
   public void setTransactionManager(
      PlatformTransactionManager transactionManager) {
      this.transactionManager = transactionManager;
   }
   @Override
   public void create(String name, Integer age, Integer marks, Integer year){
      TransactionDefinition def = new DefaultTransactionDefinition();
      // 开始事务  返回一个status对象  可以跟踪事务的状态  及时提交 或者回滚等操作
      TransactionStatus status = transactionManager.getTransaction(def);
      try {
         //  插入 数据
         String SQL1 = "insert into Student (name, age) values (?, ?)";
         jdbcTemplateObject.update( SQL1, name, age);
         // Get the latest student id to be used in Marks table
         String SQL2 = "select max(id) from Student";
         int sid = jdbcTemplateObject.queryForObject( SQL2,null,Integer.class );
         // 插入
         String SQL3 = "insert into Marks(sid, marks, year) " + 
                       "values (?, ?, ?)";
         jdbcTemplateObject.update( SQL3, sid, marks, year);
         System.out.println("Created Name = " + name + ", Age = " + age);
         transactionManager.commit(status);
      } catch (DataAccessException e) {
         System.out.println("Error in creating record, rolling back");
         transactionManager.rollback(status);
         throw e;
      }
   }
   @Override
   public List<StudentMarks> listStudents() {
      String SQL = "select * from Student, Marks where Student.id=Marks.sid";
      List <StudentMarks> studentMarks = jdbcTemplateObject.query(SQL, 
                                         new StudentMarksMapper());
      return studentMarks;
   }
}