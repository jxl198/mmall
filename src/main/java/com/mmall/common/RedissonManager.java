package com.mmall.common;

import com.mmall.util.PropertiesUtil;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.config.Config;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author jiangxl
 * @description
 * @date 2018-09-03 10:06
 **/
@Component
@Slf4j
public class RedissonManager {

    private Config config = new Config();
    private Redisson redisson = null;

    private static String masterRedisIp = PropertiesUtil.getProperty("redis.master.ip");
    private static Integer masterPort = PropertiesUtil.getInt("redis.master.port");
    private static String slaveRedisIp = PropertiesUtil.getProperty("redis.slave.ip");
    private static Integer slaveRedisPort = PropertiesUtil.getInt("redis.slave.port");

    @PostConstruct
    public void init(){
        try {
            config.useSingleServer().setAddress(new StringBuilder().append(masterRedisIp).append(":").append(masterPort).toString());
            redisson = (Redisson) Redisson.create(config);
            log.info("初始化redisson结束");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Redisson getRedisson() {
        return redisson;
    }
}
