package com.springboot.girl.design.adapter;

/**
 * @Description TODO
 * @Author GuanHuizhen
 * @Date 2019/7/10
 */
public class Chinese2EnglishAdapter implements IHandler {
    IChineseHandler chineseHandler;

    public Chinese2EnglishAdapter(IChineseHandler chineseHandler) {
        this.chineseHandler = chineseHandler;
    }

    @Override
    public void sayEnglish() {
        chineseHandler.sayChinese();
        System.out.println("he say he come from a country in china");
    }
}
