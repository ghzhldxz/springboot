package com.springboot.girl.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
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

}
