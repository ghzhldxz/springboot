package com.springboot.girl.controller;

import com.springboot.girl.bean.Account;
import com.springboot.girl.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description 访问数据库
 * @Author GuanHuizhen
 * @Date 2018/7/20
 */
@Controller
public class IndexController {
    @Autowired
    AccountService accountService;

    @RequestMapping(value = {"/index","/hello"},method = RequestMethod.GET)
    public String getIndex() {
        return "index";
    }

    @RequestMapping(value = "/transfer",method = RequestMethod.POST)
    @ResponseBody
    public String transfer(Account account) {
        System.out.println("========================"+account.getName()+",password="+account.getMoney());
        try {
            return accountService.transfer("aaa","ddd",200d);
        } catch (Exception e) {
            return e.getMessage();
        }

    }
}
