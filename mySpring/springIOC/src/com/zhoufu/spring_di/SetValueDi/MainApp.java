package com.zhoufu.spring_di.SetValueDi;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: zhoufu
 * @Date: 2021/1/14 18:12
 * @description:
 */
public class MainApp {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("DIBeans.xml");
        TextEditor obj = (TextEditor) context.getBean("Di_textEditor");
        obj.spellChecker();
    }
}
