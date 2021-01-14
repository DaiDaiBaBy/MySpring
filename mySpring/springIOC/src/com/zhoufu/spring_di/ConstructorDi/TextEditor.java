package com.zhoufu.spring_di.ConstructorDi;

/**
 * @Author: zhoufu
 * @Date: 2021/1/14 17:59
 * @description:
 */
public class TextEditor {
    private SpellChecker spellChecker;
    public TextEditor(SpellChecker spellChecker){
        System.out.println("Inside TextEditor constructor." );
        this.spellChecker = spellChecker;
    }
    public void spellChecker(){
        spellChecker.checkSpelling();
    }
}
