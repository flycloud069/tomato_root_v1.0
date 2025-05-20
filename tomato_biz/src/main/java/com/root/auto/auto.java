package com.root.auto;

import com.root.memory.LoginTokenMemory;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author ：fuyunxiang
 * @date ：Created in 2022年12月15日,0015 12:45
 */
@Component
@Log4j2
@EnableScheduling
@EnableAsync
public class auto {
    /**
     * 自动检查
     */
    @Scheduled(fixedRate=8000)
    public void AutoSocketCheck() {
        LoginTokenMemory.clearOverdue();
    }
}
