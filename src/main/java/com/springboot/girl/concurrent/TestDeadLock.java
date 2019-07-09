package com.springboot.girl.concurrent;

/**
 * @Description TODO
 * @Author GuanHuizhen
 * @Date 2019/5/31
 */
public class TestDeadLock {

    public static void main(String[] args) throws InterruptedException

    {
        Object o = new Object();

        synchronized (new Object()) {
            o.wait();
        }
    }

}

class ThreadRunA extends Thread {
    @Override
    public void run() {
        System.out.println("================A===================");
        synchronized (A.A) {
            System.out.println("我要开始执行任务A。。。。" + Thread.currentThread().getName());
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (B.B) {
            }
            System.out.println("我在执行任务结束了A。。。。" + Thread.currentThread().getName() + ":" + B.B.hashCode() + ":" + A.A.hashCode());
        }
    }
}

class ThreadRunB extends Thread {
    @Override
    public void run() {
        System.out.println("================B===================");
        synchronized (B.B) {
            System.out.println("我要开始执行任务B。。。。" + Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (A.A) {
                try {
                    B.B.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("我在执行任务结束了B。。。。" + Thread.currentThread().getName() + ":" + B.B + ":" + A.A);
        }
    }
}

class A {
    static Integer A = new Integer(1);
}

class B {
    static Integer B = new Integer(1);
}