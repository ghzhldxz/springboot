package com.springboot.girl.controller;

import com.alibaba.fastjson.JSONObject;
import com.springboot.girl.bean.BizResult;
import com.springboot.girl.bean.CodeMsg;
import com.springboot.girl.bean.vo.LoginVo;
import com.springboot.girl.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.config.AnnotationDrivenBeanDefinitionParser;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @Description 登录
 * @Author GuanHuizhen
 * @Date 2018/7/24
 */
@Controller
@RequestMapping("/login")
public class LoginController {
    Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    UserService userService;

    @RequestMapping(value = "/to_login")
    public String toLoginPage() {

        return "login";
    }

    @RequestMapping(value = "/do_login",method = RequestMethod.POST)
    @ResponseBody
    public BizResult<String> doLogin(HttpServletResponse response, @Valid LoginVo loginVo
           // , BindingResult bindingResult
    ) {
        logger.info("登录参数："+JSONObject.toJSONString(loginVo));
        //传统的校验方式
       /* if(loginVo == null || loginVo.getMobile() == null || loginVo.getPassword() == null) {
            return BizResult.failure(CodeMsg.BIND_ERROR.filterMsg("请输入用户名或密码"));
        }
        if(!ValidatorUtil.isMobile(loginVo.getMobile())) {
            return BizResult.failure(CodeMsg.MOBILE_ERROR);
        }*/
        boolean ret = userService.login(response,loginVo);
        return BizResult.success(CodeMsg.SUCCESS.filterMsg("登录成功"));
    }

}
