package com.springboot.girl.validation;

import com.alibaba.druid.util.StringUtils;
import com.springboot.girl.util.ValidatorUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @Description 手机号校验
 * @Author GuanHuizhen
 * @Date 2018/7/24
 */
public class IsMobileValidator implements ConstraintValidator<IsMobile,String> {

    private boolean require = false;

    @Override
    public void initialize(IsMobile constraintAnnotation) {
        require = constraintAnnotation.require();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(require) {
            return ValidatorUtil.isMobile(s);
        } else {
            if(StringUtils.isEmpty(s)) {
                return true;
            }else {
                return ValidatorUtil.isMobile(s);
            }
        }
    }

}
