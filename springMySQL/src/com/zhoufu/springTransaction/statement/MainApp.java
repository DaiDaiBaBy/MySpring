package com.zhoufu.springTransaction.statement;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * @Author: zhoufu
 * @Date: 2021/1/27 14:47
 * @description:
 */
public class MainApp {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("StatementBeans.xml");
        StudentDAO studentJDBCTemplate = (StudentDAO) context.getBean("studentJDBCTemplate");
        studentJDBCTemplate.create("Zara", 11, 99, 2010);
        studentJDBCTemplate.create("Nuha", 20, 97, 2010);
        studentJDBCTemplate.create("Ayan", 25, 100, 2011);

        List<StudentMarks> studentMarks = studentJDBCTemplate.listStudents();
        for (StudentMarks studentMark : studentMarks) {
            System.out.print("ID : " + studentMark.getId() );
            System.out.print(", Name : " + studentMark.getName() );
            System.out.print(", Marks : " + studentMark.getMarks());
            System.out.print(", Year : " + studentMark.getYear());
            System.out.println(", Age : " + studentMark.getAge());
        }
    }
}
