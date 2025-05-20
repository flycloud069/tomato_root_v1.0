package com.root.dto;

import lombok.Data;

@Data
public class SysExtractsServiceDTO {
    /**
     * 系统抽取组方法id
     */
    private String sys_extracts_service_id;
    /**
     * 系统抽取入参
     */
    private String inputString;
    /**
     * 定时任务参数
     */
    private String sys_extracts_service_cron;
}
