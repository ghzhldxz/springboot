package com.springboot.girl.design.proxy;

/**
 * @Description 购买者
 * @Author GuanHuizhen
 * @Date 2019/6/26
 */
public class Buyer implements IBuy{

    @Override
    public Apple buyApple() {
        System.out.println("帮我到新疆买一箱阿克苏萍果");
        return null;
    }
}
