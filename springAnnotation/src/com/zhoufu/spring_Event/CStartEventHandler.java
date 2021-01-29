package com.zhoufu.spring_Event;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStartedEvent;

/**
 * @Author: zhoufu
 * @Date: 2021/1/15 15:28
 * @description:
 */
public class CStartEventHandler implements ApplicationListener<ContextStartedEvent> {
    @Override
    public void onApplicationEvent(ContextStartedEvent contextStartedEvent) {
        System.out.println("ContextStartedEvent Received");
    }
}
