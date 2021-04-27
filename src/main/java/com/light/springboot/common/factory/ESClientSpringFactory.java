package com.light.springboot.common.factory;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * @author 李振振
 * @version 1.0
 * @date 2020/12/22 21:38
 */
public class ESClientSpringFactory {
    public static int CONNECT_TIMEOUT_MILLIS = 1000;
    public static int SOCKET_TIMEOUT_MILLIS = 30000;
    public static int CONNECTION_REQUEST_TIMEOUT_MILLIS = 500;
    public static int MAX_CONN_PER_ROUTE = 10;
    public static int MAX_CONN_TOTAL = 30;

    private static ArrayList<HttpHost> HTTP_HOST =  new ArrayList<>(Collections.emptyList());
    private RestClientBuilder builder;
    private RestClient restClient;
    private RestHighLevelClient restHighLevelClient;

    private static ESClientSpringFactory esClientSpringFactory = new ESClientSpringFactory();

    private ESClientSpringFactory(){}

    /**
     * 设置连接
     * @param httpHost 节点集合
     * @param maxConnectNum 最大连接数
     * @param maxConnectPerRoute 最大重试次数
     * @return
     */
    public static ESClientSpringFactory build(ArrayList<HttpHost> httpHost,
                                              Integer maxConnectNum, Integer maxConnectPerRoute){
        HTTP_HOST = httpHost;
        MAX_CONN_TOTAL = maxConnectNum;
        MAX_CONN_PER_ROUTE = maxConnectPerRoute;
        return  esClientSpringFactory;
    }

    /**
     * 设置连接
     * @param httpHost 节点集合
     * @param connectTimeOut 连接超时时间
     * @param socketTimeOut socket 超时时间
     * @param connectionRequestTime 连接请求时间
     * @param maxConnectNum 最大连接数
     * @param maxConnectPerRoute 最大重试次数
     * @return
     */
    public static ESClientSpringFactory build(ArrayList<HttpHost> httpHost,Integer connectTimeOut, Integer socketTimeOut,
                                              Integer connectionRequestTime,Integer maxConnectNum, Integer maxConnectPerRoute){

        HTTP_HOST = httpHost;
        CONNECT_TIMEOUT_MILLIS = connectTimeOut;
        SOCKET_TIMEOUT_MILLIS = socketTimeOut;
        CONNECTION_REQUEST_TIMEOUT_MILLIS = connectionRequestTime;
        MAX_CONN_TOTAL = maxConnectNum;
        MAX_CONN_PER_ROUTE = maxConnectPerRoute;
        return  esClientSpringFactory;
    }



    public void init(){
        builder = RestClient.builder(HTTP_HOST.toArray(new HttpHost[0]));
        setConnectTimeOutConfig();
        setMutiConnectConfig();
        restClient = builder.build();
        restHighLevelClient = new RestHighLevelClient(builder);
    }

    /**
     * 配置连接时间延时
     */
    public void setConnectTimeOutConfig(){
        builder.setRequestConfigCallback(requestConfigBuilder -> {
            requestConfigBuilder.setConnectTimeout(CONNECT_TIMEOUT_MILLIS);
            requestConfigBuilder.setSocketTimeout(SOCKET_TIMEOUT_MILLIS);
            requestConfigBuilder.setConnectionRequestTimeout(CONNECTION_REQUEST_TIMEOUT_MILLIS);
            return requestConfigBuilder;
        });
    }

    /**
     *  使用异步httpclient时设置并发连接数
     */
    public void setMutiConnectConfig(){
        builder.setHttpClientConfigCallback(httpClientBuilder -> {
            httpClientBuilder.setMaxConnTotal(MAX_CONN_TOTAL);
            httpClientBuilder.setMaxConnPerRoute(MAX_CONN_PER_ROUTE);
            return httpClientBuilder;
        });
    }

    public RestClient getClient(){
        return restClient;
    }

    public RestHighLevelClient getRhlClient(){
        return restHighLevelClient;
    }

    /**
     * 连接关闭
     */
    public void close() {
        if (restClient != null) {
            try {
                restClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}