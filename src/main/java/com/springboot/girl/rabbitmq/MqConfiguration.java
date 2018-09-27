package com.springboot.girl.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;


/**
 * @Description rabbitmq消息队列配置
 * @Author GuanHuizhen
 * @Date 2018/8/10
 */
@SpringBootConfiguration
public class MqConfiguration {

    /**
     * 注意，这里的queue是spring-amqp包中的类
     * @return
     */
    @Bean
    public Queue queue() {
        return new Queue("testQueue",true);
    }

    @Bean
    public Queue createQueue1() {
        return new Queue("q1",true);
    }

    @Bean
    public Queue createQueue2() {
        return new Queue("q2",true);
    }

    /**
     * 主题模式交换机，队列只接收指定路由关键字的消息
     * 发送时：要指定路由关键字
     * @return
     */
    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange("exchange");
    }

    @Bean
    public Binding bindingTopic1() {
       return BindingBuilder.bind(createQueue1()).to(topicExchange()).with("topic.key");
    }

    @Bean
    public Binding bindingTopic2() {
        return BindingBuilder.bind(createQueue2()).to(topicExchange()).with("topic.#");
    }

    /**
     * 广播模式交换机
     * @return
     */
    @Bean
    public Queue createFanoutQueue() {
        return new Queue("fanoutQueue1",true);
    }

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanoutExchange");
    }

    @Bean
    public Binding bindingFanout() {
        return BindingBuilder.bind(createFanoutQueue()).to(fanoutExchange());
    }

}
