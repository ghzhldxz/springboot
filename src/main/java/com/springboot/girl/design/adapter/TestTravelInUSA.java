package com.springboot.girl.design.adapter;

/**
 * @Description 对象适配器模式
 * 还有一种 接口适配器模式  比如IExternal（对外服务的接口）  IRquestBuilder(调用接口入参构造器）
 * @Author GuanHuizhen
 * @Date 2019/7/10
 */
public class TestTravelInUSA {

    public static void main(String[] args) {
        Chinese2EnglishAdapter adapter = new Chinese2EnglishAdapter(new ChineseHandler());
        adapter.sayEnglish();
    }
}
