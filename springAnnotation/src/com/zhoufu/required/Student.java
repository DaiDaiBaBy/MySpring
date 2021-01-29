package com.zhoufu.required;


import org.springframework.beans.factory.annotation.Required;

/**
 * @Author: zhoufu
 * @Date: 2021/1/15 13:52
 * @description:
 */
public class Student {
    private Integer age;
    private String name;

    public Integer getAge() {
        return age;
    }

    @Required
    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }
    @Required
    public void setName(String name) {
        this.name = name;
    }
}
