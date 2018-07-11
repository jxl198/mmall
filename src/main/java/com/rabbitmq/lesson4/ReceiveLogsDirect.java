package com.rabbitmq.lesson4;

import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @author jiangxl
 * @description
 * @date 2018-07-10 9:52
 **/
public class ReceiveLogsDirect {
    private static final String EXCHANGE_NAME = "direct_logs";

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
        String queueName = channel.queueDeclare().getQueue();
        String[] severities ={"error"};
        for(String severity:severities){
            channel.queueBind(queueName,EXCHANGE_NAME,severity);
        }
        System.out.println("waiting for message");
        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body,"utf-8");
                System.out.println("[x] received message:"+envelope.getRoutingKey()
                +" message "+msg);
            }
        };
        channel.basicConsume(queueName,true,consumer);

    }

}
