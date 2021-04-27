/*
 * 〈描述〉
 *
 * @author lzz
 * @since 版本号
 */

package com.light.springboot.config;/**
 * @author 李振振
 * @date 2021/4/26 17:14
 * @version 1.0
 */

import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

/**
 * 〈一句话功能简述〉
 *
 * <p>〈功能详细描述〉
 *
 * @author lzz
 * @since 版本号
 */
@Configuration
@EnableCaching
public class RedisConfig {
    @Value("${spring.redis.host:#{'127.0.0.1'}}")
    private String hostName;

    @Value("${spring.redis.port:#{6379}}")
    private int port;

//    @Value("${spring.redis.password:#{'009014'}}")
//    private String password;

    @Value("${spring.redis.timeout:#{3000}}")
    private int timeout;

    @Value("${spring.redis.lettuce.pool.max-idle:#{16}}")
    private int maxIdle;

    @Value("${spring.redis.lettuce.pool.min-idle:#{1}}")
    private int minIdle;

    @Value("${spring.redis.lettuce.pool.max-wait:#{16}}")
    private long maxWaitMillis;

    @Value("${spring.redis.lettuce.pool.max-active:#{16}}")
    private int maxActive;

    @Value("${spring.redis.database:#{0}}")
    private int databaseId;

    @Bean
    public LettuceConnectionFactory lettuceConnectionFactory(){
        RedisConfiguration redisConfiguration = new RedisStandaloneConfiguration(hostName,port);
        // 设置选用的数据库号码
        ((RedisStandaloneConfiguration) redisConfiguration).setDatabase(databaseId);

//        // 设置 redis 数据库密码
//        ((RedisStandaloneConfiguration) redisConfiguration).setPassword(password);

        // 连接池配置
        GenericObjectPoolConfig<Object> poolConfig = new GenericObjectPoolConfig<>();
        poolConfig.setMaxIdle(maxIdle);
        poolConfig.setMinIdle(minIdle);
        poolConfig.setMaxTotal(maxActive);
        poolConfig.setMaxWaitMillis(maxWaitMillis);

        LettucePoolingClientConfiguration.LettucePoolingClientConfigurationBuilder builder
                = LettucePoolingClientConfiguration.builder()
                .commandTimeout(Duration.ofMillis(timeout));

        LettucePoolingClientConfiguration lettucePoolingClientConfiguration = builder.build();

        builder.poolConfig(poolConfig);

        // 根据配置和客户端配置创建连接
        return new LettuceConnectionFactory(redisConfiguration, lettucePoolingClientConfiguration);
    }

    /**
     * 配置key生成规则
     * @return
     */
    @Bean
    public KeyGenerator wiselyKeyGenerator(){
        return (target,method,params) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(target.getClass().getName());
            sb.append(method.getName());
            for (Object obj : params){
                sb.append(obj.toString());
            }
            return sb.toString();
        };
    }

//    @Bean
//    public RedisTemplate<String,Object> redisTemplate(LettuceConnectionFactory lettuceConnectionFactory){
//        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
//        // 使用FastJsonRedisSeiralizer 来序列化和反序列化redis的value值
//        FastJsonRedisSerializer<Object> serializer = new FastJsonRedisSerializer<>(Object.class);
//        ParserConfig.getGlobalInstance().addAccept("com.light.springboot");
//        FastJsonConfig fastJsonConfig = new FastJsonConfig();
//        fastJsonConfig.setCharset(StandardCharsets.UTF_8);
//        serializer.setFastJsonConfig(fastJsonConfig);
//
//        // key 的 String 序列化采用 StringRedisSerializer
//        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
//        redisTemplate.setKeySerializer(stringRedisSerializer);
//        redisTemplate.setHashKeySerializer(stringRedisSerializer);
//
//        // value 的值序列化采用 fastJsonRedisSerializer
//        redisTemplate.setValueSerializer(serializer);
//        redisTemplate.setHashValueSerializer(serializer);
//        redisTemplate.afterPropertiesSet();
//        return redisTemplate;
//    }

    @Bean(name = "redisTemplate")
    public RedisTemplate<String, Object> limitRedisTemplate(
            RedisConnectionFactory redisConnectionFactory) {

        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }

    @Bean
    public StringRedisTemplate stringRedisTemplate(LettuceConnectionFactory lettuceConnectionFactory){
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
        stringRedisTemplate.setConnectionFactory(lettuceConnectionFactory);
        return stringRedisTemplate;
    }
}
