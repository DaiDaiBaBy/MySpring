package com.zhoufu.springInOut;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * @Author: zhoufu
 * @Date: 2021/1/20 14:42
 * @description:
 */
public class MainApp {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("InOutBeans.xml");
        StudentJDBCTemplate jdbcTemplate = (StudentJDBCTemplate) context.getBean("studentJDBCTemplate");
//        jdbcTemplate.create("张三", 11);
//        jdbcTemplate.create("李四",34);
//        jdbcTemplate.create("扒皮", 43);

        List<Student> students = jdbcTemplate.listStudents();
        for (Student student : students) {
            System.out.print("ID : " + student.getId() );
            System.out.print(", Name : " + student.getName() );
            System.out.println(", Age : " + student.getAge());
        }

        Student student = jdbcTemplate.getStudent(4);
        System.out.println("==========================");
        System.out.print("ID : " + student.getId() );
        System.out.print(", Name : " + student.getName() );
        System.out.println(", Age : " + student.getAge());
    }
}
