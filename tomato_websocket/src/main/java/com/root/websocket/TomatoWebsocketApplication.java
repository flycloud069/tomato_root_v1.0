package com.root.websocket;

import com.root.config.SysConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication(scanBasePackages = {"com.*","com.gitee.sunchenbin.mybatis.actable.manager.*"})
@MapperScan({"com.**.mapper","com.gitee.sunchenbin.mybatis.actable.dao.*"})
public class TomatoWebsocketApplication {

	@PostConstruct
	public void init() {
		// 在这里执行仅需在启动时执行一次的操作

	}

	public static void main(String[] args) {
		SpringApplication.run(TomatoWebsocketApplication.class, args);
	}

}
