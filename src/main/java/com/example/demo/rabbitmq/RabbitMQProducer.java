package com.example.demo.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQProducer {

    private final RabbitTemplate rabbitTemplate;
    private final String exchangeName;
    private final String routingKey;

    public RabbitMQProducer(RabbitTemplate rabbitTemplate,
                            @Value("${spring.rabbitmq.exchange}") String exchangeName,
                            @Value("${spring.rabbitmq.routing-key}") String routingKey) {
        this.rabbitTemplate = rabbitTemplate;
        this.exchangeName = exchangeName;
        this.routingKey = routingKey;
    }

    public void sendMessage(String id) {
        rabbitTemplate.convertAndSend(exchangeName, routingKey, id);
        System.out.println("Sent ID: " + id);
    }
}
