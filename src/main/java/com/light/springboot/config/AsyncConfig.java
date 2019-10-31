package com.light.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import java.util.concurrent.Executor;

/**
 * 定时任务配置类，可以多定时任务同时执行
 * Configuration 表明是配置类
 * EnableAsync 表明开启异步事件支持
 * @author 李振振
 * @version 1.0
 * @date 2019/10/29 16:44
 */
@Configuration
@EnableAsync
public class AsyncConfig  implements SchedulingConfigurer {
    /**
     * 配置定时任务执行线程池类
     * @return
     */
    @Bean(name = "scheduler")
    public Executor taskExecutor() {
        ThreadPoolTaskScheduler executor = new ThreadPoolTaskScheduler();
        executor.setPoolSize(20);
        return executor;
    }

    /**
     * 配置执行计划，使用线程池
     * @param scheduledTaskRegistrar
     */
    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.setScheduler(taskExecutor());
    }
}
