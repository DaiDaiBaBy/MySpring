package com.zhoufu.spring_ioc.SingletonBeanDemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: zhoufu
 * @Date: 2021/1/14 15:37
 * @description:
 */
public class singletonBeanMain {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
        HelloWorld objA = (HelloWorld) context.getBean("singleton_helloWorld");
        objA.setMessage("Bean A 实例");
        objA.getMessage();
        HelloWorld objB = (HelloWorld) context.getBean("singleton_helloWorld");
        objB.getMessage();
    }
}
