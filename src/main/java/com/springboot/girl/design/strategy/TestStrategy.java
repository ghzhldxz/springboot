package com.springboot.girl.design.strategy;

import com.springboot.girl.design.strategy.impl.StrategyDiscount;
import com.springboot.girl.design.strategy.impl.StrategyReturn;

/**
 * @Description TODO
 * @Author GuanHuizhen
 * @Date 2019/6/24
 */
public class TestStrategy {

    public static void main(String[] args) {
        //输入结算类型
        int type = 1 ; //满减
        Context context = null;
        switch (type) {
            case 0 :
                context = new Context(new StrategyReturn(300,50));
                break;
            case 1 :
                context = new Context(new StrategyDiscount(300,8.5));
                break;
        }
        System.out.println(context.getResult());
    }
}
