package com.springboot.girl.concurrent;

import java.util.Collections;
import java.util.Vector;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import static java.util.concurrent.Executors.*;

/**
 * @Description TODO
 * @Author GuanHuizhen
 * @Date 2019/4/20
 */
public class TestCountDownLatch {

    // 订单队列
    Vector<P> pos = new Vector<>();
    // 派送单队列
    Vector<D> dos = new Vector<>();
    /** 执行回调的线程池 */
    static Executor executor = newFixedThreadPool(6);
    final CyclicBarrier barrier =  new CyclicBarrier(2, ()->{
                executor.execute(()->check());
            });

    synchronized void check(){
        P p = pos.remove(0);
        D d = dos.remove(0);
        //System.out.println(p.label + ">>>>>" +d.label);
        if(p.num != d.num) {
            System.out.println(p.label + ">>>>>" +d.label);
        }
        // 执行对账操作
        //diff = check(p, d);
       // System.out.println("3.执行对账操作");
        // 差异写入差异库
        //save(diff);
        //System.out.println("4.差异写入差异库");
    }

    void checkAll(){
        // 循环查询订单库
        Thread T1 = new Thread(()->{
            int i = 0;
            while(true){
                // 查询订单库
               // System.out.println("1.查询订单库");
                //pos.add(getPOrders());
                i++;
                pos.add(new P("订单",i));
                // 等待
                try {
                    barrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        });
        T1.start();
        // 循环查询运单库
        Thread T2 = new Thread(()->{
            int i = 0;
            while(true){
                // 查询运单库
                //System.out.println("2.查询【运】单库");
                //dos.add(getDOrders());
                // 等待
                i ++ ;
                dos.add(new D("运单",i));
                try {
                    barrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        });
        T2.start();
    }

    public static void main(String[] args) {
        TestCountDownLatch test = new TestCountDownLatch();
        test.checkAll();
    }

}

class P {
    String label;
    int num;
    P(String label,int num) {
        this.label = label;
        this.num = num;
    }
}

class D {
    String label;
    int num;
    D(String label,int num) {
        this.label = label;
        this.num = num;
    }
}
