package com.springboot.girl.design.decorator;

/**
 * @Description TODO
 * @Author GuanHuizhen
 * @Date 2019/6/24
 */
public class TakeTrousers extends BaseCostume{
    @Override
    public void show() {
        super.show();
        System.out.println("穿裤子");
    }
}
