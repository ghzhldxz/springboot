package com.springboot.girl.design.proxy;

/**
 * @Description TODO
 * @Author GuanHuizhen
 * @Date 2019/6/26
 */
public class Seller {

    public Apple createOrder() {
        System.out.println("下单了，请创建新订单！");
        return new Apple();
    }

}
