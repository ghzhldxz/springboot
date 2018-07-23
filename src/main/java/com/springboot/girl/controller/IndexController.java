package com.springboot.girl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Description TODO
 * @Author GuanHuizhen
 * @Date 2018/7/20
 */
@Controller
public class IndexController {

    @RequestMapping(value = {"/index","/hello"},method = RequestMethod.GET)
    public String getIndex() {
        return "index";
    }
}
