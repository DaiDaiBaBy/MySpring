package com.zhoufu.springTransaction.program;

import javax.sql.DataSource;
import java.util.List;

/**
 * @Author: zhoufu
 * @Date: 2021/1/26 17:39
 * @description:
 */
public interface StudentDAO {
    /**
     * This is the method to be used to initialize
     * database resources ie. connection.
     */
    public void setDataSource(DataSource ds);
    /**
     * This is the method to be used to create
     * a record in the Student and Marks tables.
     */
    public void create(String name, Integer age, Integer marks, Integer year);
    /**
     * This is the method to be used to list down
     * all the records from the Student and Marks tables.
     */
    public List<StudentMarks> listStudents();
}
