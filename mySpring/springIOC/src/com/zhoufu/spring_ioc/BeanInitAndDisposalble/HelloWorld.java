package com.zhoufu.spring_ioc.BeanInitAndDisposalble;

/**
 * @Author: zhoufu
 * @Date: 2021/1/14 16:32
 * @description:
 */
public class HelloWorld {
    private String message;

    public void setMessage(String message) {
        this.message = message;
    }

    public void getMessage() {
        System.out.println("Your Message :" + message);
    }
    public void init(){
        System.out.println("Bean is going through init.");
    }
    public void destroy(){
        System.out.println("Bean will destroy now.");
    }
}
