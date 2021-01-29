package com.zhoufu.springJDBC;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Author: zhoufu
 * @Date: 2021/1/18 13:39
 * @description:
 */
public class StudentMapper implements RowMapper<Student> {
    @Override
    public Student mapRow(ResultSet resultSet, int row) throws SQLException {
        Student student = new Student();
        student.setId(resultSet.getInt("id"));
        student.setName(resultSet.getString("name"));
        student.setAge(resultSet.getInt("age"));
        return student;
    }
}
