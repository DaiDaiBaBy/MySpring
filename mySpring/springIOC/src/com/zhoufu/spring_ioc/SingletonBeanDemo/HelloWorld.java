package com.zhoufu.spring_ioc.SingletonBeanDemo;

public class HelloWorld {
    private String message;
    public void setMessage(String message) {
        this.message = message;
    }

    public void getMessage() {
        System.out.println("Singleton Bean to Your Message: " + message);
    }
}
