package com.zhoufu.required;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: zhoufu
 * @Date: 2021/1/15 13:58
 * @description:
 */
public class MainApp {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("RequiredBeans.xml");
        Student student = (Student) context.getBean("student");
        System.out.println("Name: " + student.getName());
        System.out.println("Age:  " + student.getAge());
    }
}
