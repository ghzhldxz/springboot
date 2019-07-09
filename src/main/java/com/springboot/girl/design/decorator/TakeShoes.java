package com.springboot.girl.design.decorator;

/**
 * @Description 鞋子
 * @Author GuanHuizhen
 * @Date 2019/6/24
 */
public class TakeShoes extends BaseCostume {
    @Override
    public void show() {
        super.show();
        System.out.println("穿鞋子");
    }
}
