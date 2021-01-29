package com.zhoufu.springInOut;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

/**
 * @Author: zhoufu
 * @Date: 2021/1/20 14:17
 * @description:
 */
public class StudentJDBCTemplate implements StudentDao {
    private DataSource dataSource;
    private SimpleJdbcCall simpleJdbcCall;
    private JdbcTemplate jdbcTemplate;
    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        // 储存过程名称getRecord
        this.simpleJdbcCall = new SimpleJdbcCall(dataSource).withProcedureName("getRecord");
    }

    @Override
    public void create(String name, Integer age) {
        String sql = "insert into Student (name, age) values (?,?)";
        jdbcTemplate.update(sql, name, age);
    }

    @Override
    public Student getStudent(Integer id) {
        // 使用msyql储存过程 SqlParameterSource
        SqlParameterSource in = new MapSqlParameterSource().addValue("in_id", id);
        Map<String, Object> out = simpleJdbcCall.execute(in);
        Student student = new Student();
        student.setId(id);
        student.setName((String) out.get("out_name"));
        student.setAge((Integer) out.get("out_age"));
        return student;
    }

    @Override
    public List<Student> listStudents() {
        String sql = "select * from Student";
        List<Student> query = jdbcTemplate.query(sql, new StudentMapper());
        return query;
    }
}
