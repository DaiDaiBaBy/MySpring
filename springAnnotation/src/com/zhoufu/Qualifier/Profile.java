package com.zhoufu.Qualifier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * @Author: zhoufu
 * @Date: 2021/1/15 14:27
 * @description:
 */
public class Profile {

    @Autowired
    @Qualifier("student1")
    private Student student;
    public Profile(){
        System.out.println("Inside Profile constructor." );
    }
    public void printAge() {
        System.out.println("Age : " + student.getAge() );
    }
    public void printName() {
        System.out.println("Name : " + student.getName() );
    }
}
