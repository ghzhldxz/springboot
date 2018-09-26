package com.springboot.girl.bean.vo;

import lombok.Data;

/**
 * @Description 导入客户代扣信息
 * @Author GuanHuizhen
 * @Date 2018/9/14
 */
@Data
public class CustomerVo {
    private String name;
    private String simpleName;
    private String trade;
    private String source;
    private String address;
    private String remark;

    @Override
    public String toString() {
        return "CustomerVo{" + "name='" + name + '\'' + ", simpleName='" + simpleName + '\'' + ", trade='" + trade + '\'' + ", source='" + source + '\'' + ", address='" + address + '\'' + ", remark='" + remark + '\'' + '}';
    }
}
