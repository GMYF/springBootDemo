package com.light.springboot.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;

/**
 * 〈一句话功能简述〉
 *
 * <p>〈功能详细描述〉
 *
 * @author lzz
 * @since 版本号
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
        dataSource.setUsername("gmyf");
        dataSource.setPassword("009014");
        dataSource.setInitialSize(5);
        dataSource.setMinIdle(5);
        dataSource.setMaxActive(10);
    }

    public static Connection getConnection() throws Exception{
        return Singleton.INSTANCE.dataSource.getConnection();
    }
}
