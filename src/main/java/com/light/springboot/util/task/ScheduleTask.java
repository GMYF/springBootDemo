package com.light.springboot.util.task;

import com.light.springboot.util.log.LogUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author 李振振
 * @version 1.0
 * @date 2019/10/29 16:53
 */
@Component
@Slf4j
public class ScheduleTask {
    /**
     * Async 表示在异步任务池中
     * 定义每5秒执行该任务
     */
    @Async
    @Scheduled(fixedRate = 5000)
    public void sendEmailScheduled(){
        LogUtil.info("邮件定时发送开始");
    }
}
