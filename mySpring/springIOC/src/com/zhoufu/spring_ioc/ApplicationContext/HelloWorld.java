package com.zhoufu.spring_ioc.ApplicationContext;

/**
 * @Author: zhoufu
 * @Date: 2021/1/14 15:06
 * @description:
 */
public class HelloWorld {
    private String message;
    public void setMessage(String message) {
        this.message = message;
    }
    public void getMessage() {
        System.out.println("Spring ApplicationContext to Your message:" + message);
    }
}
