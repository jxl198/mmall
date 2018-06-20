package com.mmall.common;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author jiangxl
 * @description 使用guava本地缓存
 * @date 2018-05-29 14:26
 **/
@Slf4j
public class TokenCache {


    public static final String TOKEN_PREFIX="token_";

    //超过10000个，就会使用LRU（最小使用算法）进行清除
    private static LoadingCache<String, String> localCache = CacheBuilder.newBuilder().initialCapacity(1000).maximumSize(10000)
            .expireAfterAccess(12, TimeUnit.HOURS).build(new CacheLoader<String, String>() {
                @Override
                public String load(String s) throws Exception {
                    return "null";
                }
            });

    public static void setKey(String key, String value) {
        localCache.put(key, value);
    }

    public static String getKey(String key) {
        String value = null;
        try {
            value = localCache.get(key);
            if ("null".equals(value)) {
                return null;
            }
        } catch (Exception e) {
            log.error("localCache get error", e);
        }
        return value;
    }

}
