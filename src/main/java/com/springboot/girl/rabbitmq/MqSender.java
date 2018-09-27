package com.springboot.girl.rabbitmq;

import com.springboot.girl.redis.RedisService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Queue;

/**
 * @Description 消息发送方
 * @Author GuanHuizhen
 * @Date 2018/8/10
 */
@Slf4j
@Service
public class MqSender {
    @Autowired
    AmqpTemplate amqpTemplate;

    public <T> void sendMsg(T msg) {
        String message = RedisService.beanToString(msg);
        log.info("生成消息："+message);
        amqpTemplate.convertAndSend("testQueue",message);
    }

    public <T> void sendTopicMsg1(T msg) {
        String message = RedisService.beanToString(msg);
        log.info("生成topic消息1："+message);
        amqpTemplate.convertAndSend("exchange","topic.key",message);
    }

    public <T> void sendTopicMsg2(T msg) {
        String message = RedisService.beanToString(msg);
        log.info("生成topic消息2："+message);
        amqpTemplate.convertAndSend("exchange","topic.name",message);
    }

    public <T> void sendTopicMsg3(T msg) {
        String message = RedisService.beanToString(msg);
        log.info("生成topic消息3："+message);
        amqpTemplate.convertAndSend("exchange","topic.key11",message);
    }

    public <T> void sendFanoutMsg(T msg) {
        String message = RedisService.beanToString(msg);
        log.info("生成fanoutExchange消息3："+message);
        amqpTemplate.convertAndSend("fanoutExchange","",message);//路由关键字为空（不能省略）

    }

}
