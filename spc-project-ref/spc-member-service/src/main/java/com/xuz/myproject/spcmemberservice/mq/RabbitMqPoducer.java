package com.xuz.myproject.spcmemberservice.mq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqPoducer {
    @Autowired
    RabbitTemplate rabbitTemplate;

    public void sendMail(String queue,String json) {
        rabbitTemplate.setQueue(queue);
        rabbitTemplate.convertAndSend(queue, json);
    }
}
