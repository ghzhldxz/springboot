package com.springboot.girl.Exception;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description TODO
 * @Author GuanHuizhen
 * @Date 2018/11/12
 */
public class OOMTest {

    public static void main(String args[]){
        System.out.println("begin ...");

        List<BigObject> list = new ArrayList<BigObject>();
        for(int i=0;i<Integer.MAX_VALUE;i++){

            new BigObject();//for ygc
            list.add(new BigObject());//memory leak

            if(i % 100 == 1){//gc frequency,i% number, the number the bigger,gc more quickly
                System.out.println("gc ...");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println("end ...");
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    static class BigObject{
        private char data[];

        public BigObject() {
            this.data = new char[1024*10];
        }
    }
}
