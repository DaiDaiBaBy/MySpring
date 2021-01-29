package com.zhoufu.Qualifier;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: zhoufu
 * @Date: 2021/1/15 14:29
 * @description:
 */
public class MainApp {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("QualifierBeans.xml");
        Profile obj = (Profile) context.getBean("profile");
        obj.printAge();
        obj.printName();
    }
}
