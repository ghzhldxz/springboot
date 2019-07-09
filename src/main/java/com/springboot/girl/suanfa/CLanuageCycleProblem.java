package com.springboot.girl.suanfa;

/**
 * @Description c语言中数据越界无限循环问题验证
 * @Author GuanHuizhen
 * @Date 2018/11/12
 */
public class CLanuageCycleProblem {

    public static void main(String[] args) {
        method();
    }

    public static void method () {
        int i = 0;
        int j = 0;
        int[] arr = new int[3];
        String[] str = new String[3];
        System.out.println("i="+System.identityHashCode(i));
        System.out.println("j="+System.identityHashCode(i));//说明值相同的基本类型，指向的是同一个地址的数据（0只有一份）
        System.out.println("arr="+System.identityHashCode(arr));
        System.out.println("str="+System.identityHashCode(str));

    }
}
