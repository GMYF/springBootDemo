package com.light.springboot.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;

/**
 * @author 李振振
 * @version 1.0
 * @date 2019/9/3 16:40
 */
public class DruidConfig {

    private static interface Singleton{
        final DruidConfig INSTANCE = new DruidConfig();
    }
    private DruidDataSource dataSource ;
    private DruidConfig (){
        // druid数据源配置
        this.dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/jira?verifyServerCertificate=false&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=GMT%2B8");
        dataSource.setUsername("root");
        dataSource.setPassword("009014");
        dataSource.setInitialSize(5);
        dataSource.setMinIdle(5);
        dataSource.setMaxActive(10);
    }

    public static Connection getConnection() throws Exception{
        return Singleton.INSTANCE.dataSource.getConnection();
    }
}
