package com.zhoufu.spring_Event;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: zhoufu
 * @Date: 2021/1/15 15:31
 * @description:
 */
public class MainApp {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("EventBeans.xml");
        context.start();  // 触发事件
        HelloWorld obj = (HelloWorld)context.getBean("helloWorld");
        obj.getMessage();
        context.stop(); // 触发事件
    }
}
