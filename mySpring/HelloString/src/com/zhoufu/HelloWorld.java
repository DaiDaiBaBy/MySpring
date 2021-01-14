package com.zhoufu;

/**
 * @Author: zhoufu
 * @Date: 2021/1/14 14:00
 * @description:
 */
public class HelloWorld {
    private String message;

    public void setMessage(String message) {
        this.message = message;
    }

    public void getMessage() {
        System.out.println("Your message : " + message);
    }
}
