package org.cch.exercise.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class Sender {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Scheduled(fixedRate=1000)
    public void heartBeat()
    {
        HeartBeat heartBeat = new HeartBeat(LocalDateTime.now());
        System.out.println("Sending heart beat: " + heartBeat);

        rabbitTemplate.convertAndSend("heart-beat", heartBeat);
    }

    @RabbitListener(queues = "acked")
    public void processAck(Message<Ack> message)
    {
        System.out.println("received ack: " + message.getPayload());
    }

}
