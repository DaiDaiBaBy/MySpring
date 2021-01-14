package com.zhoufu.spring_di.ConstructorDi;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: zhoufu
 * @Date: 2021/1/14 18:01
 * @description:
 */
public class MainApp {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("DIBeans.xml");
        TextEditor textEditor = (TextEditor) context.getBean("textEditor");
        textEditor.spellChecker();
    }
}
