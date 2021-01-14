package com.zhoufu.spring_ioc.PrototypeBeanDemo;

import com.zhoufu.spring_ioc.PrototypeBeanDemo.HelloWorld;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: zhoufu
 * @Date: 2021/1/14 15:37
 * @description:
 */
public class PrototypeBeanMain {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
        HelloWorld objA = (HelloWorld) context.getBean("prototype_helloWorld");
        objA.setMessage("Bean A 实例");
        objA.getMessage();
        HelloWorld objB = (HelloWorld) context.getBean("prototype_helloWorld");
        objB.getMessage(); // null
    }
}
