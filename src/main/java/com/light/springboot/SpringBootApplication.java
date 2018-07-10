package com.light.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;

@org.springframework.boot.autoconfigure.SpringBootApplication
@ComponentScan("com.light.springboot")
@MapperScan("com.light.springboot")
public class SpringBootApplication {
    public static void main(String [] args){
        SpringApplication.run(SpringBootApplication.class,args);
    }
}
