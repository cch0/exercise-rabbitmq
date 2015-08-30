package org.cch.exercise.rabbitmq;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties
public class RabbitMQProperties {
    String queueName;
    String ackName;

    public String getAckName() {
        return ackName;
    }

    public void setAckName(String ackName) {
        this.ackName = ackName;
    }

    public String getQueueName() {
        return queueName;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }
}
