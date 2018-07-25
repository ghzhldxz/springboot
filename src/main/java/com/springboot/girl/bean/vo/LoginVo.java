package com.springboot.girl.bean.vo;

import com.springboot.girl.validation.IsMobile;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Description 登录VO
 * @Author GuanHuizhen
 * @Date 2018/7/24
 */
public class LoginVo implements Serializable {
    private static final long serialVersionUID = 8351672587071659125L;

    @NotNull
    @IsMobile(message="手机号码输入有误")
    private String mobile;

    @NotNull
    @Length(min = 6,max = 32)
    private String password;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
