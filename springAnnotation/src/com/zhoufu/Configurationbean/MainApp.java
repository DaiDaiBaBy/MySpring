package com.zhoufu.Configurationbean;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author: zhoufu
 * @Date: 2021/1/15 14:48
 * @description:
 */
public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(HelloConfiguration.class);
        HelloWorld bean = context.getBean(HelloWorld.class);
        bean.setMessage("注解配置 config");
        bean.getMessage();
    }
}
