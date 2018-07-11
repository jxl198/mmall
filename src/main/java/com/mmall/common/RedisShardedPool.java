package com.mmall.common;

import com.mmall.util.PropertiesUtil;
import redis.clients.jedis.*;
import redis.clients.util.Hashing;
import redis.clients.util.Sharded;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jiangxl
 * @description
 * @date 2018-07-11 10:26
 **/
public class RedisShardedPool {
    private static ShardedJedisPool pool;           //sharded jedis 连接池
    private static Integer maxTotal = PropertiesUtil.getInt("redis.max.total", 20);       //最大连接数
    private static Integer maxIdle = PropertiesUtil.getInt("redis.max.idle", 10);        //最大空闲的jedis实例的个数
    private static Integer minIdle = PropertiesUtil.getInt("redis.min.idle", 2);        //最小空闲jedis实例的个数
    private static Boolean testOnBorrow = PropertiesUtil.getBoolean("redis.test.borrow", true);   //是否进行验证操作，若为true，则得到的jedis实例肯定可用
    private static Boolean testOnReturn = PropertiesUtil.getBoolean("redis.test.return", true);    //是否进行验证操作，若为true，则放回的jedis肯定可用

    private static String masterRedisIp = PropertiesUtil.getProperty("redis.master.ip");
    private static Integer masterPort = PropertiesUtil.getInt("redis.master.port");
    private static String slaveRedisIp = PropertiesUtil.getProperty("redis.slave.ip");
    private static Integer slaveRedisPort = PropertiesUtil.getInt("redis.slave.port");

    private static void initPool() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(maxTotal);
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMinIdle(minIdle);
        jedisPoolConfig.setTestOnBorrow(testOnBorrow);
        jedisPoolConfig.setTestOnReturn(testOnReturn);
        jedisPoolConfig.setBlockWhenExhausted(true);  //连接池 满的时候是否阻塞
        JedisShardInfo info1 = new JedisShardInfo(masterRedisIp, masterPort, 1000 * 2);
//        如果有密码需要调用
//        info1.setPassword("xxxx");

        JedisShardInfo info2 = new JedisShardInfo(slaveRedisIp, slaveRedisPort, 1000 * 2);
        List<JedisShardInfo> jedisShardInfoList = new ArrayList<JedisShardInfo>(2);
        jedisShardInfoList.add(info1);
        jedisShardInfoList.add(info2);
        pool = new ShardedJedisPool(jedisPoolConfig, jedisShardInfoList, Hashing.MURMUR_HASH, Sharded.DEFAULT_KEY_TAG_PATTERN);
    }


    static {
        initPool();
    }

    public static ShardedJedis getJedis() {
        return pool.getResource();
    }

    public static void close(ShardedJedis jedis) {
        if (jedis != null) {
            jedis.close();
        }

    }

    public static void main(String[] args) {
        ShardedJedis jedis = getJedis();
        for (int i = 0; i < 10; i++) {
            jedis.set("key" + i, "value" + i);
        }
        close(jedis);
        System.out.println("program is end");

    }
}
