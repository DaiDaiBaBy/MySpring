package com.zhoufu.MySpringEvent;

import org.springframework.context.ApplicationListener;

/**
 * @Author: zhoufu
 * @Date: 2021/1/15 16:39
 * @description:   事件处理
 */
// listener
public class CustomEventHandler implements ApplicationListener<CustomEvent>{
    @Override
    public void onApplicationEvent(CustomEvent customEvent) {
        System.out.println(customEvent.toString());
    }
}
