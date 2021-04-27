package com.light.springboot.config;

import com.light.springboot.common.factory.ESClientSpringFactory;
import com.light.springboot.util.string.StringUtils;
import lombok.Data;
import org.apache.http.HttpHost;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @author 李振振
 * @version 1.0
 * @date 2020/12/17 17:54
 */

@Data
@Configuration
@ConfigurationProperties(prefix = "elasticsearch",ignoreUnknownFields = false)
@PropertySource("classpath:elasticsearch.properties")
public class ElasticSearchApiConfig {
    private static Logger logger = LogManager.getLogger(ElasticSearchApiConfig.class);
    private static final int ADDRESS_LENGTH = 2;
    private static final String HTTP_SCHEME = "http";
    /**
     * 使用冒号隔开ip和端口1
     */
    String ipAddress;

    @Bean
    public ArrayList<HttpHost> httpHost(){
        ArrayList<HttpHost> hosts = new ArrayList<HttpHost>(Collections.emptyList());
        Arrays.stream(ipAddress.split(",")).forEach(host -> {
            assert !StringUtils.isEmpty(host);
            String[] address = host.split(":");
            if (address.length == ADDRESS_LENGTH) {
                String ip = address[0];
                int port = Integer.parseInt(address[1]);
                hosts.add(new HttpHost(ip, port, HTTP_SCHEME));
            }
        });
        return hosts;
    }
    /**
     * 构造es的集群信息，生成RestClient客户端
     * @return RestClient客户端
     */
    @Bean(initMethod="init",destroyMethod="close")
    public ESClientSpringFactory getFactory(){
        return ESClientSpringFactory.
                build(httpHost(), HttpConfig.MAX_CONN,
                        HttpConfig.MAX_PRE_ROUTE);
    }


    @Bean("highLevelClient")
    public RestHighLevelClient getRestHighLevelClient() {
        return getFactory().getRhlClient();
    }
}
