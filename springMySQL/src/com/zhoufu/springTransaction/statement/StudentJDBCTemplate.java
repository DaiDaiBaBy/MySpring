package com.zhoufu.springTransaction.statement;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

/**
 * @Author: zhoufu
 * @Date: 2021/1/27 14:35
 * @description:
 */
public class StudentJDBCTemplate implements StudentDAO {
    private JdbcTemplate jdbcTemplateObject;

    @Override
    public void setDataSource(DataSource ds) {
        this.jdbcTemplateObject = new JdbcTemplate(ds);
    }

    @Override
    public void create(String name, Integer age, Integer marks, Integer year) {
        try {
            String sql1 = "insert into Student (name, age) values (?,?)";
            jdbcTemplateObject.update(sql1, name, age);

            String sql2 = "select max(id) from Student";
            int sid = jdbcTemplateObject.queryForObject(sql2, Integer.class);

            String sql3 = "insert into Marks(sid,marks,year) values(?,?,?)";
            int update = jdbcTemplateObject.update(sql3, sid, marks, year);
            System.out.println("Created Name = " + name + ", Age = " + age);
            throw new RuntimeException("simulate Error condition");
        } catch (DataAccessException e){
            System.out.println("Error in creating record, rolling back");
            throw  e;
        }
    }

    @Override
    public List<StudentMarks> listStudents() {
        String sql = "select * from Student, Marks where Student.id=Marks.sid";
        List<StudentMarks> studentMarks = jdbcTemplateObject.query(sql, new StudentMarksMapper());
        return studentMarks;
    }
}
