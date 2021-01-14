package com.zhoufu.spring_ioc.BeanPostProcessor;

import com.zhoufu.spring_ioc.BeanPostProcessor.HelloWorld;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: zhoufu
 * @Date: 2021/1/14 17:00
 * @description:
 */
public class MainApp {
    /**
     * 需要注册一个在 ​AbstractApplicationContext​ 类中声明的关闭​ hook​ 的 ​registerShutdownHook() ​方法
     * 它将确保正常关闭，并且调用相关的 ​destroy​ 方法。
     */
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
        HelloWorld obj = (HelloWorld) context.getBean("beanPostProcessor_helloWorld");
        obj.getMessage();
        context.registerShutdownHook();
    }
}
