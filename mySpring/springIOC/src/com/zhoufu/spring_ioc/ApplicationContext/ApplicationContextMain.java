package com.zhoufu.spring_ioc.ApplicationContext;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * @Author: zhoufu
 * @Date: 2021/1/14 15:05
 * @description:
 */
public class ApplicationContextMain {
    public static void main(String[] args) {
        ApplicationContext context =
                new FileSystemXmlApplicationContext("D:\\tool\\develop\\gitRespository\\MySpring\\mySpring\\springIOC\\src\\Beans.xml");
        HelloWorld obj = (HelloWorld) context.getBean("applicationContext_helloWorld");
        obj.getMessage();
    }
}
