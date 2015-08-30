package org.cch.exercise.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

    @RabbitListener(queues = "heart-beat")
    @SendTo("acked")
    public Ack processHeartBeat(Message<HeartBeat> message)
    {
        System.out.println("received heart beat: " + message.getPayload());

        return new Ack(message.getPayload().time);
    }
}
