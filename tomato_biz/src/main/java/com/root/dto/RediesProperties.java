package com.root.dto;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @ClassName RediesProperties
 * @Author fuyunxiang
 * @Date 2021/4/20 14:27
 * @Description RediesProperties
 * @Version 1.0
 */
@Data
@Component
@ConfigurationProperties(prefix = "spring.redis")
public class RediesProperties {
    private  int database;
    private String host;
    private int port;
    private String password;
    private int timeout;

}
