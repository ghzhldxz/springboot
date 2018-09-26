package com.springboot.girl;

import com.alibaba.fastjson.JSONObject;
import com.springboot.girl.bean.MiaoshaGoods;
import com.springboot.girl.bean.User;
import com.springboot.girl.bean.vo.GoodsVo;
import com.springboot.girl.rabbitmq.MqSender;
import com.springboot.girl.service.GoodsService;
import com.springboot.girl.service.MiaoshaService;
import com.springboot.girl.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;


/**
 * @Description 测试类
 * @Author GuanHuizhen
 * @Date 2018/7/25
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class Test1 {

    @Autowired
    Queue queue;
    @Autowired
    MqSender mqSender;

    @Autowired
    OrderService orderService;
    @Autowired
    MiaoshaService miaoshaService;
    @Autowired
    GoodsService goodsService;

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
    @Test
    public void sendMsg() {
        mqSender.sendTopicMsg1(new StringBuilder("你好 exchange1"));
        mqSender.sendTopicMsg2(new StringBuilder("你好 exchange2"));
        mqSender.sendTopicMsg2(new StringBuilder("你好 exchange3"));
    }

    @Test
    @Transactional
    public void testCreateOrder() {
        Test1 t = null;
        try {
            t = Test1.testChar(Test1.class);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        User user = new User();
        user.setId(15800000000L);
        GoodsVo goods = goodsService.queryGoodVoByGoodsId(1l);
        MiaoshaGoods miaoshaGoods = miaoshaService.queryMiaoshaGoodsByGoodsId(1l);
        goods.setMiaoshaPrice(miaoshaGoods.getMiaoshaPrice());
        goods.setStockCount(1);
        orderService.createOrder(user,goods);
    }

    public static <T> T testChar(Class superClass) throws IllegalAccessException, InstantiationException {
        return (T) superClass.newInstance();
    }





}
