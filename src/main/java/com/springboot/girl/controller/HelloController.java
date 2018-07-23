package com.springboot.girl.controller;

import com.springboot.girl.bean.BizResult;
import com.springboot.girl.properties.GirlProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description helloSpringboot
 * @Author GuanHuizhen
 * @Date 2018/7/20
 */
@RestController
public class HelloController {
    //方式一：直接导入属性值
//    @Value("${girl.age}")
//    private String age;

    //当属性多时，可进行分类按类型建属性的实体
    @Autowired
    GirlProperty girlProperty;

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String getIndex() {
        return "Hello SpringBoot the girl's cupSize is "+this.girlProperty.getCupSize()+" and age is "+this.girlProperty.getAge();
    }

    @RequestMapping(value="/result",method = RequestMethod.GET)
    public BizResult<String> testResult() {
        return BizResult.success("HAHA");
    }

}
