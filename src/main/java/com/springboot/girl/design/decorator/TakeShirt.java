package com.springboot.girl.design.decorator;

/**
 * @Description 衬杉
 * @Author GuanHuizhen
 * @Date 2019/6/24
 */
public class TakeShirt extends BaseCostume{

    @Override
    public void show() {
        super.show();
        System.out.println("穿衬衫");
    }
}
