package com.root.dto;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @ClassName PoolProperties
 * @Author fuyunxiang
 * @Date 2021/4/21 10:12
 * @Description PoolProperties
 * @Version 1.0
 */
@Data
@Component
@ConfigurationProperties(prefix = "spring.redis.jedis.pool")
public class PoolProperties {
    private int  maxactive;
    private int  maxwait;
    private int  maxidle;
    private int  minidle;

}
