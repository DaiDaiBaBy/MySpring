package com.zhoufu.Configurationbean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: zhoufu
 * @Date: 2021/1/15 14:47
 * @description:
 */
@Configuration
public class HelloConfiguration {
    @Bean
    public HelloWorld helloWorld(){
        return new HelloWorld();
    }
}
