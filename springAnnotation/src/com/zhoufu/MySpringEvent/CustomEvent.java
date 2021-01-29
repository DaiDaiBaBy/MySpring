package com.zhoufu.MySpringEvent;

import org.springframework.context.ApplicationEvent;


/**
 * @Author: zhoufu
 * @Date: 2021/1/15 16:29
 * @description:   定义事件
 */
public class CustomEvent extends ApplicationEvent {
    public CustomEvent(Object source) {
        super(source);
    }
    @Override
    public String toString(){
        return "My Custom Event";
    }
}

