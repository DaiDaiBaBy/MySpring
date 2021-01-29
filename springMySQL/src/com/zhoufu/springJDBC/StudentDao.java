package com.zhoufu.springJDBC;

import javax.sql.DataSource;
import java.util.List;

/**
 * @Author: zhoufu
 * @Date: 2021/1/18 13:33
 * @description:  数据访问层
 */
public interface StudentDao {

    public void setDataSource(DataSource ds);

    public void create(String name, Integer age);

    public Student getStudent(Integer id);

    public List<Student> listStudents();

    public void delete(Integer id);

    public void update(Integer id, Integer age);
}
