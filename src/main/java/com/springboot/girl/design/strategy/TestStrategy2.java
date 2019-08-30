package com.springboot.girl.design.strategy;

import com.springboot.girl.design.strategy.impl.StrategyDiscount;
import com.springboot.girl.design.strategy.impl.StrategyReturn;

import java.util.HashSet;
import java.util.Set;

/**
 * @Description TODO
 * @Author GuanHuizhen
 * @Date 2019/6/24
 */
public class TestStrategy2 {

    public static void main(String[] args) {
        //输入结算类型
        int type = 0 ; //满减
        Context2Factory context = new Context2Factory(type,300,50);
        System.out.println(context.getResult());
    }
}
