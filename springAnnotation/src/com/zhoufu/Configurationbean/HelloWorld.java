package com.zhoufu.Configurationbean;

/**
 * @Author: zhoufu
 * @Date: 2021/1/15 14:46
 * @description:
 */
public class HelloWorld {
    private String message;

    public void getMessage() {
        System.out.println("Your Message : " + message);
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
