package com.mmall.common;

import com.mmall.util.PropertiesUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author jiangxl
 * @description
 * @date 2018-06-26 9:45
 **/
public class RedisPool {

    private static JedisPool pool;           //jedis 连接池
    private static Integer maxTotal = PropertiesUtil.getInt("redis.max.total", 20);       //最大连接数
    private static Integer maxIdle = PropertiesUtil.getInt("redis.max.idle", 10);        //最大空闲的jedis实例的个数
    private static Integer minIdle = PropertiesUtil.getInt("redis.min.idle", 2);        //最小空闲jedis实例的个数
    private static Boolean testOnBorrow = PropertiesUtil.getBoolean("redis.test.borrow", true);   //是否进行验证操作，若为true，则得到的jedis实例肯定可用
    private static Boolean testOnReturn = PropertiesUtil.getBoolean("redis.test.return", true);    //是否进行验证操作，若为true，则放回的jedis肯定可用

    private static String redisIp = PropertiesUtil.getProperty("redis.ip");
    private static Integer port = PropertiesUtil.getInt("redis.port");

    private static void initPool() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(maxTotal);
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMinIdle(minIdle);
        jedisPoolConfig.setTestOnBorrow(testOnBorrow);
        jedisPoolConfig.setTestOnReturn(testOnReturn);
        jedisPoolConfig.setBlockWhenExhausted(true);  //连接池 满的时候是否阻塞
        pool = new JedisPool(jedisPoolConfig, redisIp, port);  //创建连接池
    }


    static {
        initPool();
    }

    public static Jedis getJedis() {
        return pool.getResource();
    }

    public static void close(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }

    }

    public static void main(String[] args) {
        Jedis jedis = getJedis();
        jedis.set("name", "jxl");
        close(jedis);
        pool.destroy();  //临时调用，销毁连接池中的所有拦截
        System.out.println("program is end ");
    }
}
