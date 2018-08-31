package com.mmall.util;

import com.mmall.common.RedisShardedPool;
import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.ShardedJedis;

/**
 * @author jiangxl
 * @description
 * @date 2018-07-11 10:55
 **/
@Slf4j
public class RedisShardedPoolUtil {
    public static String set(String key, String value) {
        ShardedJedis jedis = null;
        String result = null;

        try {
            jedis = RedisShardedPool.getJedis();
            result = jedis.set(key, value);
        } catch (Exception e) {
            log.error("set key :{} value :{} error ", key, value, e);
        } finally {
            RedisShardedPool.close(jedis);
            return result;
        }

    }

    public static String get(String key) {
        ShardedJedis jedis = null;
        String result = null;

        try {
            jedis = RedisShardedPool.getJedis();
            result = jedis.get(key);
        } catch (Exception e) {
            log.error("get key :{} error ", key, e);
        } finally {
            RedisShardedPool.close(jedis);
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
        ShardedJedis jedis = null;
        String result = null;
        try {
            jedis = RedisShardedPool.getJedis();
            result = jedis.setex(key,exTime,value);
        } catch (Exception e) {
            log.error("set ex :{} value :{} error ", key,value, e);
        } finally {
            RedisShardedPool.close(jedis);
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
        ShardedJedis jedis = null;
        Long result = null;
        System.out.println("ceshi-------------------------");
        try {
            jedis = RedisShardedPool.getJedis();
            result = jedis.expire(key,exTime);
        } catch (Exception e) {
            log.error("expire key :{}  error ", key, e);
        } finally {
            RedisShardedPool.close(jedis);
            return result;
        }
    }

    public static Long del(String key) {
        ShardedJedis jedis = null;
        Long result = null;

        try {
            jedis = RedisShardedPool.getJedis();
            result = jedis.del(key);
        } catch (Exception e) {
            log.error("del key :{} error ", key, e);
        } finally {
            RedisShardedPool.close(jedis);
            return result;
        }
    }


    public static Long setnx(String key, String value) {
        ShardedJedis jedis = null;
        Long result = null;

        try {
            jedis = RedisShardedPool.getJedis();
            result = jedis.setnx(key, value);
        } catch (Exception e) {
            log.error("setnx key :{} value :{} error ", key, value, e);
        } finally {
            RedisShardedPool.close(jedis);
            return result;
        }

    }

    /**
     * 返回旧值，设置新值，具有原子性
     * @param key
     * @param value
     * @return
     */
    public static String getSet(String key, String value) {
        ShardedJedis jedis = null;
        String result = null;

        try {
            jedis = RedisShardedPool.getJedis();
            result = jedis.getSet(key, value);
        } catch (Exception e) {
            log.error("getSet key :{} value :{} error ", key, value, e);
        } finally {
            RedisShardedPool.close(jedis);
            return result;
        }

    }


}
