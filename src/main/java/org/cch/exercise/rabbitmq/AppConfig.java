package org.cch.exercise.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(RabbitMQProperties.class)
public class AppConfig {

    @Autowired
    RabbitMQProperties rabbitMQProperties;

    @Bean(name="heartbeat")
    Queue queue() {
        return new Queue(rabbitMQProperties.getQueueName(), false);
    }

    @Bean(name="ack")
    Queue ackQueue() {
        return new Queue(rabbitMQProperties.getAckName(), false);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange("rabbit-exchange");
    }

    @Bean
    Binding binding(@Qualifier("heartbeat") Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(rabbitMQProperties.getQueueName());
    }

    @Bean
    Binding ackBinding(@Qualifier("ack") Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(rabbitMQProperties.getAckName());
    }
}
