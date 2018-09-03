package com.mmall.task;

import com.mmall.common.Const;
import com.mmall.common.RedissonManager;
import com.mmall.service.IOrderService;
import com.mmall.util.PropertiesUtil;
import com.mmall.util.RedisShardedPoolUtil;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.util.concurrent.TimeUnit;

/**
 * @author jiangxl
 * @description 定时关单任务
 * @date 2018-08-02 16:40
 **/
@Component
@Slf4j
public class CloseOrderTask {
    @Autowired
    private IOrderService iOrderService;
    @Autowired
    private RedissonManager redissonManager;

    // @Scheduled(cron="0 */1 * * * ?")  //每个1分钟的整数倍执行一次
    public void closeOrderTaskV1() {
        log.info("关闭订单定时任务启动");
        int hour = Integer.parseInt(PropertiesUtil.getProperty("close.order.task.time.hour"));
        iOrderService.closeOrder(hour);
        log.info("关闭订单定时任务结束");

    }

    // @PreDestroy
    private void delLock() {
        RedisShardedPoolUtil.del(Const.REDIS_LOCK.CLOSE_ORDER_TASK_LOCK);
    }

    //  @Scheduled(cron = "0 */1 * * * ?")  //每个1分钟的整数倍执行一次
    public void closeOrderTaskV2() {
        log.info("关闭订单定时任务启动");
        long lockTimeout = Long.parseLong(PropertiesUtil.getProperty("lock.timeout"));
        Long setnxResult = RedisShardedPoolUtil.setnx(Const.REDIS_LOCK.CLOSE_ORDER_TASK_LOCK, String.valueOf(System.currentTimeMillis() + lockTimeout));
        if (setnxResult != null && setnxResult.intValue() == 1) {
            //如果返回1，则获取锁成功，返回0则获取锁失败
            closeOrder(Const.REDIS_LOCK.CLOSE_ORDER_TASK_LOCK);

        } else {
            log.info("没有获取分布式锁:{}", Const.REDIS_LOCK.CLOSE_ORDER_TASK_LOCK);
        }
        log.info("关闭订单定时任务结束");

    }

    private void closeOrder(String lockName) {
        RedisShardedPoolUtil.expire(lockName, 50);//设置50s有效期，防止死锁
        log.info("获取{}，ThreadName:{}", lockName, Thread.currentThread().getName());
        int hour = Integer.parseInt(PropertiesUtil.getProperty("close.order.task.time.hour"));
        iOrderService.closeOrder(hour);
        RedisShardedPoolUtil.del(lockName);
        log.info("释放{}，ThreadName:{}", lockName, Thread.currentThread().getName());
        log.info("======================================================================");

    }

    @Scheduled(cron = "0 */1 * * * ?")  //每个1分钟的整数倍执行一次
    public void closeOrderTaskV3() {
        log.info("关闭订单定时任务启动");
        long lockTimeout = Long.parseLong(PropertiesUtil.getProperty("lock.timeout"));
        Long setnxResult = RedisShardedPoolUtil.setnx(Const.REDIS_LOCK.CLOSE_ORDER_TASK_LOCK, String.valueOf(System.currentTimeMillis() + lockTimeout));
        if (setnxResult != null && setnxResult.intValue() == 1) {
            //如果返回1，则获取锁成功，返回0则获取锁失败
            closeOrder(Const.REDIS_LOCK.CLOSE_ORDER_TASK_LOCK);

        } else {
            //未获取到锁，继续判断，判断时间戳，看是否可以重置并获取到锁
            String lockValueStr = RedisShardedPoolUtil.get(Const.REDIS_LOCK.CLOSE_ORDER_TASK_LOCK);
            if (lockValueStr != null && System.currentTimeMillis() > Long.parseLong(lockValueStr)) {//锁可以失效
                String getSetResult = RedisShardedPoolUtil.getSet(Const.REDIS_LOCK.CLOSE_ORDER_TASK_LOCK, String.valueOf(System.currentTimeMillis() + lockTimeout));
                if (getSetResult == null || (getSetResult != null && getSetResult.equals(lockValueStr))) {
                    closeOrder(Const.REDIS_LOCK.CLOSE_ORDER_TASK_LOCK);
                } else {
                    log.info("没有获取分布式锁:{}", Const.REDIS_LOCK.CLOSE_ORDER_TASK_LOCK);
                }
            } else {
                log.info("没有获取分布式锁:{}", Const.REDIS_LOCK.CLOSE_ORDER_TASK_LOCK);
            }

        }
        log.info("关闭订单定时任务结束");

    }

    //@Scheduled(cron = "0 */1 * * * ?")  //每个1分钟的整数倍执行一次
    public void closeOrderTaskV4() {

        RLock rLock = redissonManager.getRedisson().getLock(Const.REDIS_LOCK.CLOSE_ORDER_TASK_LOCK);
        boolean getLock = false;
        try {
            if (getLock = rLock.tryLock(0, 5, TimeUnit.SECONDS)) {
                log.info("Redisson 获取到分布式锁：{}，thread name :{}", Const.REDIS_LOCK.CLOSE_ORDER_TASK_LOCK, Thread.currentThread().getName());
                int hour = Integer.parseInt(PropertiesUtil.getProperty("close.order.task.time.hour"));
                iOrderService.closeOrder(hour);
            } else {
                log.info("Redisson 没有获取到分布式锁：{}，thread name :{}", Const.REDIS_LOCK.CLOSE_ORDER_TASK_LOCK, Thread.currentThread().getName());
            }

        } catch (InterruptedException e) {
            log.error("Redisson 分布式锁获取异常", e);
        } finally {
            if (!getLock) {
                return;
            }
            rLock.unlock();
            log.info("Redisson 释放锁");

        }


    }

}

