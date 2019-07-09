package com.springboot.girl.suanfa;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Description LRU算法继承LinkedHashMap
 * @Author GuanHuizhen
 * @Date 2019/4/25
 */
public class LRUTEST2 {
    static MyLRU<String,String> myLRU = new MyLRU(1);
    public static void main(String[] args) {
        System.out.println(myLRU.visit("one","haha"));
        myLRU.visit("tow","heihei");
        myLRU.visit("three","engheng");

        myLRU.visit("four","haha");
        myLRU.visit("five","eiei");
        myLRU.visit("six","engheng");
        myLRU.visit("seven","aaaa");
        myLRU.visit("eight","mama");
        myLRU.visit("nine","qndy");
        System.out.println(myLRU);
        myLRU.visit("tow","miao");
        myLRU.visit("three","wang");
        myLRU.visit("ten","aaa");
        //myLRU.visit("tow","maya");
        System.out.println(myLRU);
    }

}

/**
 * LinkedHashMap有两种访问模式 false对应的就是FIFO先进先出  true对应的就是LRU淘汰算法
 * 要使用此链表还需要重写removeEldestNode方法,否则每次插入都会删除最后一个元素
 * @param <K>
 * @param <V>
 */
class MyLRU<K,V> extends LinkedHashMap<K,V>{
    private final int CACHE_SIZE;

    //
    public boolean removeEldestNode(Map.Entry<K,V> node) {
        return size() > CACHE_SIZE;
    }

    public MyLRU(int cacheSize) {
        // true 表示让 linkedHashMap 按照访问顺序来进行排序，最近访问的放在头部，最老访问的放在尾部。
        super((int) Math.ceil(cacheSize / 0.75) + 1, 0.75f, true);
        CACHE_SIZE = cacheSize;
    }

    public V visit(K key,V value) {
        this.containsValue(value);
        if(this.get(key)==null || !this.get(key).equals(value)) {
            this.put(key,value);
        }
        return this.get(key);
    }

}


