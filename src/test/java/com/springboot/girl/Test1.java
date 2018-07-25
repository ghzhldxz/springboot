package com.springboot.girl;

import com.alibaba.fastjson.JSONObject;
import com.springboot.girl.bean.User;
import org.junit.Assert;
import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * @Description 测试类
 * @Author GuanHuizhen
 * @Date 2018/7/25
 */
public class Test1 {

    @Test
    public void testOne() {
        String str= "{'id':'123456'}";
        User user = JSONObject.parseObject(str,User.class);
        Assert.assertEquals(123456l,user.getId().longValue());
        System.out.println(user.getId());

        Jedis jedis = new Jedis("10.0.4.171",6379);
        jedis.auth("123456");
        jedis.set("01","heihei");
    }

}
