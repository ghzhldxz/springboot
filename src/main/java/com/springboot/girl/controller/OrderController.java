package com.springboot.girl.controller;

import com.springboot.girl.bean.BizResult;
import com.springboot.girl.bean.Order;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description 订单
 * @Author GuanHuizhen
 * @Date 2018/8/21
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    @RequestMapping("/detail")
    @ResponseBody
    public BizResult<Order> orderDetail(@RequestParam("orderId") Long orderId) {
        return BizResult.success(new Order());
    }

}
