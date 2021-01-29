package com.zhoufu.MySpringEvent;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

/**
 * @Author: zhoufu
 * @Date: 2021/1/15 16:34
 * @description:   事件发布
 */
public class CustomEventPublisher implements ApplicationEventPublisherAware {
    private ApplicationEventPublisher publisher;
    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }
    public void publish(){
        CustomEvent ce = new CustomEvent(this);
        publisher.publishEvent(ce);
    }
}
