package com.light.springboot.config;

/**
 * Http配置类
 * @author 李振振
 * @version 1.0
 * @date 2019/11/25 11:34
 */
public class HttpConfig {
    /**
     * 数据传输过程中数据包之间间隔的最大时间
     */
    public static final   Integer SOCKET_TIME_OUT = 30000;
    /**
     * 连接建立时间，三次握手完成时间
     */
    public static final Integer CONNECTION_TIME_OUT = 30000;
    /**
     * 从连接池获取连接的超时时间
     */
    public static final Integer CONNECTION_REQUEST_TIME_OUT = 30000;
    public static final Integer MAX_CONN = 640;
    public static final Integer MAX_PRE_ROUTE = 320;
    public static final Integer HTPP_IDEL_TIME_OUT = 5*1000;
    public static final Integer HTTP_MONITOR_INTERVAL = 10*1000;

}
