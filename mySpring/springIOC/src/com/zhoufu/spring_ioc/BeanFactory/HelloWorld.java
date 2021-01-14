package com.zhoufu.spring_ioc.BeanFactory;

/**
 * @Author: zhoufu
 * @Date: 2021/1/14 14:50
 * @description:
 */
public class HelloWorld {
    private String message;
    public void setMessage(String message) {
        this.message = message;
    }

    public void getMessage() {
        System.out.println("Spring BeanFactory to Your Message: " + message);
    }
}
