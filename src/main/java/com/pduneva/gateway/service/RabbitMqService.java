package com.pduneva.gateway.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqService {

    private final RabbitTemplate rabbitTemplate;
    private final String exchangeName;
    private final String queueName;

    public RabbitMqService(
            RabbitTemplate rabbitTemplate,
            @Value("${queue.name}") String queueName,
            @Value("${exchange.name}") String exchangeName
    ) {
        this.rabbitTemplate = rabbitTemplate;
        this.queueName = queueName;
        this.exchangeName = exchangeName;
    }

    public void sendMessage(String message) {
        rabbitTemplate.convertAndSend(exchangeName, queueName, message);
    }
}
