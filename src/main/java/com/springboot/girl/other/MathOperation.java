package com.springboot.girl.other;

import com.alibaba.druid.sql.visitor.functions.Char;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description 用栈实现数学运算
 * @Author GuanHuizhen
 * @Date 2018/11/14
 */
public class MathOperation {
    private static String[] numbersArr = {"0","1","2","3","4","5","6","7","8","9"};
    public static void main(String[] args) {
        MyStack<Integer> stackNum = new MyStack<>(20);
        MyStack<String> stackOp = new MyStack<>(20);
        String str = "2+2*3+(9+6)*2";
        List figure = Arrays.asList(numbersArr);
        for(int i=0;i<str.length();i++) {
            char element = str.charAt(i);
            if(figure.contains(element)) {
                stackNum.push(new Integer(element));
            } else {
                String op = stackOp.pop();
                int ret = compare(op,element+"");
            }
        }
    }

    /**
     *
     * @param op
     * @param s
     * @return 0 同级  -1 低优先级  1 高优先级  -2 符号不可比较
     */
    private static int compare(String op, String s) {
        return 0;
    }

}

class MyStack<T> {
    private Object[] items;
    private int count;//栈中元素个数，栈顶指针
    private int n;//栈的大小

    public  MyStack(int n) {
        this.n = n;
        this.count = 0;
        items = new Object[n];
    }

    public T pop() {
        if(count == 0) {
            return null;
        }
        return (T) items[--count];
    }

    public boolean push(T element) {
        if(count == n) {
            resize();
        }
        items[count++] = element;
        return true;
    }

    public void resize() {
        Object[] newItems = new Object[2*n];
        for(int i=0;i<items.length;i++) {
            newItems[i] = items[i];
        }
        items = newItems;
        count = items.length;

    }
}
