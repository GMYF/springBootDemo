package com.light.springboot.config;

import com.light.springboot.util.log.LogUtil;
import org.junit.Test;

/**
 * @author 李振振
 * @version 1.0
 * @date 2019/9/3 17:29
 */
public class ConfigTest {
    @Test
    public void logger2DataBaseTest(){
        LogUtil.info("日志已经写入数据库了");
    }
}
