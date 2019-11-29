package com.light.springboot.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
/**
 * @author 李振振
 * @version 1.0
 * @date 2019/11/29 17:29
 */
@Component
@ConfigurationProperties(prefix = "application")
public class FileConfig {
    @Setter
    @Getter
    private String version;
    @Getter
    @Setter
    private String profile;
    @Getter
    @Setter
    private String upload;
}
