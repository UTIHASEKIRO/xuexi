package com.zkzl.module.test.controller.admin.generate;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test/rabbitMqDemo")
public class RabbitMqDemo {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/send")
    public void send(String message){
        rabbitTemplate.convertAndSend("zkzl_topic_exchange","zkzl.haha",message);
        System.err.println("已发送: "+message);
    }
}
