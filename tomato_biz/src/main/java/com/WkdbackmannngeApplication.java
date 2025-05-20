package com;

import com.root.config.SysConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.PostConstruct;

@SpringBootApplication(scanBasePackages = {"com.*", "com.gitee.sunchenbin.mybatis.actable.manager.*"})
@MapperScan({"com.**.mapper", "com.gitee.sunchenbin.mybatis.actable.dao.*"})
@ServletComponentScan
public class WkdbackmannngeApplication {

    public static void main(String[] args) {
        SpringApplication.run(WkdbackmannngeApplication.class, args);
    }

}
