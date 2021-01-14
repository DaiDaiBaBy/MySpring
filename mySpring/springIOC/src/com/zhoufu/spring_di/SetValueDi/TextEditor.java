package com.zhoufu.spring_di.SetValueDi;

/**
 * @Author: zhoufu
 * @Date: 2021/1/14 17:59
 * @description:
 */
public class TextEditor {
    private Checker checker;

    public void setChecker(Checker checker){
        System.out.println("Inside setChecker." );
        this.checker = checker;
    }

    public Checker getChecker() {
        return checker;
    }

    public void spellChecker(){
        checker.checkSpelling();
    }
}
