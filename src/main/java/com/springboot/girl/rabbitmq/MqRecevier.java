package com.springboot.girl.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description 消息接收方
 * @Author GuanHuizhen
 * @Date 2018/8/10
 */
@Service
@Slf4j
public class MqRecevier {

    @RabbitListener(queues = "testQueue")
    public void receive (String msg) {
        log.info("我收到了消息："+msg);
    }

    @RabbitListener(queues = "q1")
    public void receiveTopicMsg (String msg) {
        log.info("我收到了q1消息："+msg);
    }

    @RabbitListener(queues = "q2")
    public void receiveTopicMsg2 (String msg) {
        log.info("我收到了q2消息："+msg);
    }

   // @RabbitListener(queues = "fanoutQueue1")
    public void receiveFanoutMsg (String msg) {
        log.info("我收到了fanoutExchange消息："+msg);
    }


}
