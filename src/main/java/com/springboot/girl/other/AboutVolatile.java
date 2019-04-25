package com.springboot.girl.other;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description volatile原理探讨
 * @Author GuanHuizhen
 * @Date 2019/2/18
 */
public class AboutVolatile {
    volatile int a = 1;
    int b = 1;

    public void setV() {
        a ++ ;
        b ++ ;
    }

    public static void main(String[] args) {
        String test = "haha";
        String[] test2 = test.split(",");
        AboutVolatile aboutVolatile = new AboutVolatile();
        LinkedTransferQueue atomicInteger = null;
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                aboutVolatile.setV();
                System.out.println(aboutVolatile.a+",b="+aboutVolatile.b);
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                aboutVolatile.setV();
                System.out.println(aboutVolatile.a+",b="+aboutVolatile.b);
            }
        });
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                aboutVolatile.setV();
                System.out.println(aboutVolatile.a+",b="+aboutVolatile.b);
            }
        });
        t3.start();
        t1.start();
        t2.start();
    }
}
