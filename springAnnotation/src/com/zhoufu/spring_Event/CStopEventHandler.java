package com.zhoufu.spring_Event;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStoppedEvent;

/**
 * @Author: zhoufu
 * @Date: 2021/1/15 15:29
 * @description:
 */
public class CStopEventHandler implements ApplicationListener<ContextStoppedEvent> {
    @Override
    public void onApplicationEvent(ContextStoppedEvent contextStoppedEvent) {
        System.out.println("ContextStoppedEvent Received");
    }
}
