package com.springboot.girl.concurrent;

import java.util.List;
import java.util.Vector;
import java.util.concurrent.Semaphore;
import java.util.function.Function;

/**
 * @Description TODO
 * @Author GuanHuizhen
 * @Date 2019/5/28
 */
public class TestSemaphore {

    public static void main(String[] args) {
        // 创建对象池
     /*   ObjPool<String, Integer> pool = new ObjPool(10, "2");
        // 通过对象池获取 t，之后执行
        pool.exec(t -> {
            System.out.println(t);
            return Integer.valueOf(t);
        });*/
        new TestSemaphore().test(2);
    }

    public void test(int i) {
        while(true) {
            System.out.println("haha-"+i);
            i ++ ;
        }

    }
}

class ObjPool<T, R> {
    final List<T> pool;
    // 用信号量实现限流器
    final Semaphore sem;
    // 构造函数
    ObjPool(int size, T t){
        pool = new Vector<T>(){};
        for(int i=0; i<size; i++){
            pool.add(t);
        }
        sem = new Semaphore(size);
    }
    // 利用对象池的对象，调用 func
    R exec(Function<T,R> func) {
        T t = null;
        try {
            sem.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            t = pool.remove(0);
            return func.apply(t);
        } finally {
            pool.add(t);
            sem.release();
        }
    }
}

