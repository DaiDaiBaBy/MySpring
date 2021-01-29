package com.zhoufu.aopXml;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: zhoufu
 * @Date: 2021/1/15 17:23
 * @description:
 */
public class MainApp {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("AopBeans.xml");
        Student obj = (Student)context.getBean("student");
        obj.getName();
        obj.getAge();
        obj.printThrowException();
    }
}
