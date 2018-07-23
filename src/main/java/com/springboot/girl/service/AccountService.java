package com.springboot.girl.service;

/**
 * @Description 转账服务
 * @Author GuanHuizhen
 * @Date 2018/7/23
 */
public interface AccountService {

    public String transfer(String in,String out,Double money) throws Exception;
}
