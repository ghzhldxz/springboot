package com.springboot.girl.Exception;

import java.util.Arrays;

/**
 * @Description 数值
 * @Author GuanHuizhen
 * @Date 2019/4/25
 */
public class NumForamt {

    public static void main(String[] args) {
        String subFileName = String.format("%02d",1);
        String subFileName2 = String.format("%02d",11);
        String subFileName3 = String.format("%02d",5);
        String[] carPhoto = {"interior","mileage","nameplate","outward"};
        System.out.println(Arrays.asList(carPhoto).contains("jxq"));
    }

}
