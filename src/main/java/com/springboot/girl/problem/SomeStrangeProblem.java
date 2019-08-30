package com.springboot.girl.problem;

import com.springboot.girl.design.strategy.Context2Factory;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Description 一些常见的问题验证
 * @Author GuanHuizhen
 * @Date 2019/7/11
 */
public class SomeStrangeProblem {
    public static void main(String[] args) {

        //开发中常见的问题
        //问题一：
        Set<Integer> set1 = new HashSet<>();
        for(int i=0;i<100;i++) {
            set1.add(i);
            set1.remove(i-1);
        }
        System.out.println("set1 size="+set1.size());

        Set<Short> set2 = new HashSet<>();
        for(Short i=0;i<10;i++) {
            set2.add(i);
            set1.remove(i-1);//有运算符的情况下 i-1的结果是int类型。又因为Set中存放的是Object  i-1会自动装箱为Integer.
            //remove会调用Object.equals方法比较两个对象是否相等，如下，Integer.equals源码表示，传入的不是Integer类型的返回false
        }

        System.out.println("set2 size="+set2.size());
        System.out.println("Integer.equal(Short)="+new Integer(1).equals(new Short("1")));
        //问题二：
        //比较运算符是优先的，根据输出结果，发现三目运算符是否有自动转换的现象
        //小结：
        // 1.三目运算符会将结果转换为高精度数据类型
        //2. 而int 和 char 不会转换。因为char底层就是int，不存在精度的问题
        Object result = 1 == 1 ? new Long(3) : new Float(1);
        Object result2 = 1 == 1 ? 3 : 1.0d;
        Object result3 = 1 == 1 ? 3 : "a";
        Object result4 = new Integer(1) == new Integer(1) ? new Short("3") : new Integer(1);
        System.out.println("最终转换成Float类型："+result);
        System.out.println("最终会出现类型转换吗："+result2);
        System.out.println("Integer跟Char不会转换："+result3);
        System.out.println("Integer跟Short也有精度转换问题"+result4.equals(new Short("3")));
        System.out.println("最终会出现类型转换吗："+(result4 instanceof Integer));
        System.out.println("最终会出现类型转换吗："+(result4 instanceof Short));

        //问题三：
        Map<Long,String> map = new HashMap<>();
        map.put(1111L,"one");
        map.put(2222L,"two");
        map.put(3333L,"three");

        Set<Long> keySet = map.keySet();
        Object[] keyArr = keySet.toArray();
    }

}
