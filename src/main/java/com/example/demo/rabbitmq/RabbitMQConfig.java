package com.example.demo.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.beans.factory.annotation.Value;

@Configuration
public class RabbitMQConfig {

    // Values from application.properties
    @Value("${spring.rabbitmq.username}")
    private String username;

    @Value("${spring.rabbitmq.password}")
    private String password;

    @Value("${spring.rabbitmq.host}")
    private String host;

    @Value("${spring.rabbitmq.virtual-host}")
    private String virtualHost;

    // Queue, Exchange, and Routing key names
    public static final String QUEUE_NAME = "user_id_queue";
    public static final String EXCHANGE_NAME = "user_id_exchange";
    public static final String ROUTING_KEY = "user.id.routing.key";

    // Define Queue
    @Bean
    public Queue queue() {
        return new Queue(QUEUE_NAME, false);  // Non-durable queue
    }

    // Define Exchange
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    // Define Binding between Queue and Exchange using Routing Key
    @Bean
    public Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
    }
}
