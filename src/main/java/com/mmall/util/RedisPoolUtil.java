package com.mmall.util;

import com.mmall.common.RedisPool;
import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;

/**
 * @author jiangxl
 * @description redis 连接工具
 * @date 2018-06-26 15:11
 **/
@Slf4j
public class RedisPoolUtil {

    public static String set(String key, String value) {
        Jedis jedis = null;
        String result = null;

        try {
            jedis = RedisPool.getJedis();
            result = jedis.set(key, value);
        } catch (Exception e) {
            log.error("set key :{} value :{} error ", key, value, e);
        } finally {
            RedisPool.close(jedis);
            return result;
        }

    }

    public static String get(String key) {
        Jedis jedis = null;
        String result = null;

        try {
            jedis = RedisPool.getJedis();
            result = jedis.get(key);
        } catch (Exception e) {
            log.error("get key :{} error ", key, e);
        } finally {
            RedisPool.close(jedis);
            return result;
        }
    }

    /**
     *
     * @param key
     * @param value
     * @param exTime  单位是秒
     * @return
     */
    public static String setEx(String key,String value,int exTime) {
        Jedis jedis = null;
        String result = null;
        try {
            jedis = RedisPool.getJedis();
            result = jedis.setex(key,exTime,value);
        } catch (Exception e) {
            log.error("set ex :{} value :{} error ", key,value, e);
        } finally {
            RedisPool.close(jedis);
            return result;
        }
    }

    /**
     * 设置key的有效期
     * @param key
     * @param exTime  单位是秒
     * @return
     */
    public static Long expire(String key,int exTime) {
        Jedis jedis = null;
        Long result = null;
        try {
            jedis = RedisPool.getJedis();
            result = jedis.expire(key,exTime);
        } catch (Exception e) {
            log.error("expire key :{}  error ", key, e);
        } finally {
            RedisPool.close(jedis);
            return result;
        }
    }

    public static Long del(String key) {
        Jedis jedis = null;
        Long result = null;

        try {
            jedis = RedisPool.getJedis();
            result = jedis.del(key);
        } catch (Exception e) {
            log.error("del key :{} error ", key, e);
        } finally {
            RedisPool.close(jedis);
            return result;
        }
    }


    public static void main(String[] args) {
        RedisPoolUtil.set("keyTest","value");
        String value = RedisPoolUtil.get("keyTest");
        log.info("get value :{}",value);

        RedisPoolUtil.setEx("keyEx","valueex",60);
        RedisPoolUtil.expire("keyTest",20*60);
        RedisPoolUtil.del("keyTest");
        System.out.println("end");
    }


}
