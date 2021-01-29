package com.zhoufu.MySpringEvent;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: zhoufu
 * @Date: 2021/1/15 16:40
 * @description:
 */
public class MainApp {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("MyEvent.xml");
        CustomEventPublisher publisher = (CustomEventPublisher) context.getBean("customEventPublisher");
        publisher.publish();
        publisher.publish();
    }
}
