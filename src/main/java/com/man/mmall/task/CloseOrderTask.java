package com.man.mmall.task;

import com.man.mmall.common.Const;
import com.man.mmall.service.IOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class CloseOrderTask {


    @Autowired
    private IOrderService orderService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Value("${lock.timeout}")
    private Integer lockTimeout;


    @Scheduled(cron = "0 */1 * * * ?")
    @SuppressWarnings("unchecked")
    public void closeOrderTask() {
        log.info("关闭订单定时任务启动");
        Boolean setResult = (Boolean) redisTemplate.execute(connection -> connection.set(
                Const.REDIS_LOCK.CLOSE_ORDER_TASK_LOCK.getBytes(),
                String.valueOf(System.currentTimeMillis() + lockTimeout).getBytes(),
                Expiration.from(lockTimeout, TimeUnit.SECONDS), RedisStringCommands.SetOption.SET_IF_ABSENT), true);
        if (setResult) {
            log.info("获取{},ThreadName:{}", Const.REDIS_LOCK.CLOSE_ORDER_TASK_LOCK, Thread.currentThread().getName());
            orderService.closeOrder(lockTimeout);
            redisTemplate.delete(Const.REDIS_LOCK.CLOSE_ORDER_TASK_LOCK);
        }
        log.info("关闭订单定时任务结束");
    }


}

