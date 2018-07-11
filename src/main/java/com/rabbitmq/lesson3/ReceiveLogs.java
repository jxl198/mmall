package com.rabbitmq.lesson3;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author jiangxl
 * @description
 * @date 2018-07-10 9:37
 **/
public class ReceiveLogs {
    private static final String EXCHANGE_NAME = "logs";

    public static void main(String[] args) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try {
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            //声明路由及路由的类型
            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);
            String msg = "message";
            channel.basicPublish(EXCHANGE_NAME, "", null, msg.getBytes("utf-8"));
            String queueName=  channel.queueDeclare().getQueue();
            channel.queueBind(queueName,EXCHANGE_NAME,"");
            System.out.println("waiting for message");
            Consumer consumer = new DefaultConsumer(channel){
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String msg = new String(body,"utf-8");
                    System.out.println("[x] received message:"+msg);
                }
            };
            channel.basicConsume(queueName,consumer);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

}
