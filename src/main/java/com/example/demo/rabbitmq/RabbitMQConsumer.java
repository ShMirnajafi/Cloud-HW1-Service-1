package com.example.demo.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQConsumer {

    // Listener method that listens to messages from the defined queue
    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void receiveMessage(String id) {
        // Logic to handle the received message (in this case, the "id")
        System.out.println("Received ID: " + id);
    }
}
