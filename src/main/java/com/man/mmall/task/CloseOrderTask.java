package com.man.mmall.task;

import com.man.mmall.service.IOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CloseOrderTask {

    private final int TASK_TIME_HOUR = 2;

    @Autowired
    private IOrderService orderService;

    @Scheduled(cron = "0 */1 * * * ?")
    public void closeOrderTaskV1(){
        log.info("关闭订单定时任务启动");
        int hour = TASK_TIME_HOUR;
        orderService.closeOrder(hour);
        log.info("关闭订单定时任务结束");
    }
}

