package com.springboot.girl.bean;

/**
 * @Description 返回码和返回信息
 * @Date 2018/7/23
 */
public class CodeMsg {

    private int code;
    private String msg;
    public static final CodeMsg SUCCESS = new CodeMsg(200,"%s");

    public static final CodeMsg GLOBAL_ERROR = new CodeMsg(500,"系统异常，请联系管理员");
    public static final CodeMsg BIND_ERROR = new CodeMsg(5001001,"参数校验异常：%s");
    public static final CodeMsg SERVER_ERROR = new CodeMsg(5001002,"服务异常");
    public static final CodeMsg MOBILE_ERROR = new CodeMsg(5001003,"手机输入格式错误");
    public static final CodeMsg USER_EMPTY = new CodeMsg(5001004,"查无此用户");
    public static final CodeMsg PASSWORD_ERROR = new CodeMsg(5001005,"登录密码错误");
    public static final CodeMsg MOBILE_NOT_EXIST = new CodeMsg(5001006,"手机号不存在");
    public CodeMsg(int code,String msg) {
        this.code = code;
        this.msg = msg;
    }

    public CodeMsg filterMsg(Object...objects) {
        this.msg = String.format(this.msg, objects);
        return this;

    }
    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
