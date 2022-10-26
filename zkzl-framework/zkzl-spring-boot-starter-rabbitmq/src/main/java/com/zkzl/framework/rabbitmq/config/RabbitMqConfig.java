package com.zkzl.framework.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    //定义交换机的名字
    public static final String  EXCHANGE_NAME = "zkzl_topic_exchange";
    //定义队列的名字
    public static final String QUEUE_NAME = "zkzl_queue";

    //1、声明交换机
    @Bean("zkzlExchange")
    public Exchange bootExchange(){

        return ExchangeBuilder.topicExchange(EXCHANGE_NAME).durable(true).build();
    }



    //2、声明队列
    @Bean("zkzlQueue")
    public Queue bootQueue(){

        return QueueBuilder.durable(QUEUE_NAME).build();
    }


    //3、队列与交换机进行绑定
    @Bean
    public Binding bindQueueExchange(@Qualifier("zkzlQueue") Queue queue, @Qualifier("zkzlExchange") Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("zkzl.#").noargs();
    }

}
