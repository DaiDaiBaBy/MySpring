package com.zhoufu.spring_ioc.BeanPostProcessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @Author: zhoufu
 * @Date: 2021/1/14 16:57
 * @description:
 */

/**
 * 这是实现​ BeanPostProcessor ​的非常简单的例子
 * 它在任何 ​bean​ 的初始化的之前和之后输入该 ​bean​ 的名称
 *
 * 你可以在初始化 ​bean ​的之前和之后实现更复杂的逻辑，因为你有两个访问内置 ​bean​ 对象的后置处理程序的方法
 */
public class InitHelloWorld implements BeanPostProcessor{
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("BeforeInitialization :" +beanName);
        //可以根据beanName不同执行不同的处理操作
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("AfterInitialization :" + beanName);
        // 可以根据beanName不同执行不同的处理操作
        return bean;
    }
}
