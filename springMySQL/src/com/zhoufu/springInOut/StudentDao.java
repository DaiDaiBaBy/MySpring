package com.zhoufu.springInOut;

import javax.sql.DataSource;
import java.util.List;

/**
 * @Author: zhoufu
 * @Date: 2021/1/20 14:10
 * @description:
 */
public interface StudentDao {

    public void setDataSource(DataSource dataSource);

    public void create(String name, Integer age);

    public Student getStudent(Integer id);

    public List<Student> listStudents();
}
