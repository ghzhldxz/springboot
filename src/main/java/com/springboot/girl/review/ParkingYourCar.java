package com.springboot.girl.review;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description 模拟停车
 * @Author GuanHuizhen
 * @Date 2019/7/17
 */
public class ParkingYourCar {
    public static final int MAX_CAPACITY = 10;

    public static final ReentrantLock lock = new ReentrantLock();
    public static Condition condition;

    static {
        condition = lock.newCondition();
    }

    static int count = 0;

    public static void carOut() {
        try {
            lock.lock();
            //防止操作人员随意点击
            if(count <= 0) {
                System.out.println("车库为空");
                count = 0;
            } else {
                count --;
                System.out.println(Thread.currentThread()+":车子出库,剩余车位="+(MAX_CAPACITY - count));
            }
            condition.signalAll();
        }  finally {
            lock.unlock();
        }
    }

    public static void carIn() {
        try {
            lock.lock();
            while (count >= MAX_CAPACITY) {
                System.out.println(Thread.currentThread() + "车位已满，请等待~");
                condition.await();
            }
            count ++;
            System.out.println(Thread.currentThread()+":车子入库成功,剩余车位="+(MAX_CAPACITY - count));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        for(int i=0;i<2;i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        carIn();
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }
            },"in-"+i).start();
        }

        for(int i=0;i<1;i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        carOut();
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }
            },"out-"+i).start();
        }


    }
}
