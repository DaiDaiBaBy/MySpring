package com.zhoufu.springJDBC;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

/**
 * @Author: zhoufu
 * @Date: 2021/1/18 13:41
 * @description:
 */
public class StudentJDBCTemplate implements StudentDao {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    @Override
    public void setDataSource(DataSource ds) {
        this.dataSource = ds;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void create(String name, Integer age) {
        String sql = "insert into Student (name, age) values (?,?)";
        jdbcTemplate.update(sql, name, age);
        System.out.println("Created Record name=" + name+ "Age=" + age);
    }

    @Override
    public Student getStudent(Integer id) {
        String sql = "select * from Student where id = ?";
        Student student = jdbcTemplate.queryForObject(sql, new Object[]{id}, new StudentMapper());
        return student;
    }

    @Override
    public List<Student> listStudents() {
        String sql = "select * from Student";
        List<Student> students = jdbcTemplate.query(sql, new StudentMapper());
        System.out.println(students);
        return students;
    }

    @Override
    public void delete(Integer id) {
        String sql = "delete from Student where id = ?";
        jdbcTemplate.update(sql, id);
        System.out.println("Deleted Record With Id:" + id);
    }

    @Override
    public void update(Integer id, Integer age) {
        String sql = "update Student set age = ? where id = ?";
        jdbcTemplate.update(sql, age, id);
        System.out.println("Update Record With Id = " + id);
    }
}
