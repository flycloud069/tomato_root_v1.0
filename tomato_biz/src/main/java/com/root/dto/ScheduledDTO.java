package com.root.dto;

import cn.hutool.core.date.DateUtil;
import com.root.auto.SysExtractServiceTask;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;

import java.util.concurrent.ScheduledFuture;


@Data
public class ScheduledDTO {
    @Autowired
    private TaskScheduler taskScheduler;
    /**
     *用户登录信息对象
     */
    private LoginDto loginDto;
    /**
     *创建时间
     */
    private String creatTime;
    /**
     *定时周期
     */
    private String cron;
    /**
     *系统抽取服务组id
     */
    private String sys_extracts_service_id;
    /***
     * 计划对象
     */
    private ScheduledFuture<?> scheduledFuture;

    /**
     * 取消定时任务
     */
    public void cancel() {
        ScheduledFuture<?> scheduledFuture = this.scheduledFuture;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(true);
        }
    }
    public    ScheduledDTO (LoginDto loginDto ,String cron,String sys_extracts_service_id,String inputString) {
        this.loginDto=loginDto;
        this.cron=cron;
        this.creatTime= DateUtil.now();
        this.sys_extracts_service_id=sys_extracts_service_id;
        SysExtractServiceTask sysExtractServiceTask =new SysExtractServiceTask().init(sys_extracts_service_id,inputString);
        this.scheduledFuture=new ConcurrentTaskScheduler().schedule(sysExtractServiceTask,new CronTrigger(cron));

    }



}
