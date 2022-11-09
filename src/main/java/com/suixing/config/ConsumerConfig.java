package com.suixing.config;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ConsumerConfig {
    @RabbitListener(queues = "delayed-queue-order")
    public void getMsg(String msg, Channel channel, Message message){
        System.out.println("消费者收到的消息是：" + msg);
        try{
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
