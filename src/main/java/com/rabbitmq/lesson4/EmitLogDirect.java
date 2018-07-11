package com.rabbitmq.lesson4;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @author jiangxl
 * @description
 * @date 2018-07-10 9:49
 **/
public class EmitLogDirect {
    private static final String EXCHANGE_NAME = "direct_logs";

    public static void main(String[] args) throws Exception{
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
        String severity = "info";
        String message =" ...........i am  msg ........";
        channel.basicPublish(EXCHANGE_NAME,severity,null,message.getBytes("utf-8"));
        System.out.println("[x] send "+severity+" :message "+message);
        channel.close();
        connection.close();

    }
}
