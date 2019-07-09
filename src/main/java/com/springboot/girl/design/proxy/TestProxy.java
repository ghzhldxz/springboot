package com.springboot.girl.design.proxy;

/**
 * @Description TODO
 * @Author GuanHuizhen
 * @Date 2019/6/26
 */
public class TestProxy {

    public static void main(String[] args) {
        Proxy proxy = new Proxy(new Buyer());
        proxy.buyApple();
    }
}
