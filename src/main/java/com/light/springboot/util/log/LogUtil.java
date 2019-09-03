package com.light.springboot.util.log;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.util.Properties;

/**
 * @author 李振振
 * @version 1.0
 * @date 2019/9/3 14:35
 */
public class LogUtil {
    private static Logger logger = LogManager.getLogger(LogUtil.class);
    public static void info(String content) {
        Properties props = System.getProperties();
        InetAddress address = null;
        try {
            address = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            logger.info("UnknownHostException");
            e.printStackTrace();
        }
        ThreadContext.put("TYPE","1");
        ThreadContext.put("CREATE_TIME",new Timestamp(System.currentTimeMillis()).toString());
        ThreadContext.put("HOST",address.getHostAddress());
        ThreadContext.put("SYSTEM_INFO",props.getProperty("os.name")+"-"+props.getProperty("os.arch"));
        ThreadContext.put("DB_CONNECT","1");
        logger.info(">>>"+content);
    }
}
