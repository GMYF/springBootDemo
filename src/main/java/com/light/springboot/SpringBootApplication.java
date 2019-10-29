package com.light.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * EnableScheduling 开启定时任务
 * MapperScan iBatis主键扫描
 * @author lzz
 */
@org.springframework.boot.autoconfigure.SpringBootApplication
@MapperScan("com.light.springboot")
@EnableScheduling
public class SpringBootApplication {
    public static void main(String [] args){
        SpringApplication.run(SpringBootApplication.class,args);
    }
}
