package com.springboot.girl.Exception;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description TODO
 * @Author GuanHuizhen
 * @Date 2019/6/12
 */
public class TestOutOfMemory {

    public static void main(String args[]) throws InterruptedException {
        List<Object> list = new ArrayList<>();
        for(int i=0;i<100000;i++) {
            Object o = new Object();
            list.add(o);
            o = null;
        }
        Thread.sleep(22000);
        for(int i=0;i<100000;i++) {
            Object o = new Object();
            list.add(o);
            o = null;
        }
        Thread.sleep(12000000);
     }
}
