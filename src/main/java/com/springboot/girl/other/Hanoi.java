package com.springboot.girl.other;

import java.util.HashMap;

/**
 * @Description TODO
 * @Author GuanHuizhen
 * @Date 2018/12/20
 */
public class Hanoi {
    public void hanoi(int n, char A, char B, char C) {
        if (n == 1) {
            move(A, C);
        } else {
            hanoi(n - 1, A, C, B);//步骤1 按ACB数序执行N-1的汉诺塔移动
            move(A, C);             //步骤2   执行最大盘子移动
            hanoi(n - 1, B, A, C);//步骤3 按BAC数序执行N-1的汉诺塔移动
        }
    }

    private void move(char A, char C) {//执行最大盘子的从A-C的移动
        System.out.println("move:" + A + "--->" + C);
    }

    public static void main(String[] args) {
        Hanoi hanoi = new Hanoi();
        System.out.println("移动汉诺塔的步骤：");
        hanoi.hanoi(5, 'a', 'b', 'c');
    }
}

