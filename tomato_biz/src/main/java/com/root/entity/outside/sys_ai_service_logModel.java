package com.root.entity.outside;
import lombok.Data;

@Data
public class sys_ai_service_logModel {
    /**
     * 系统AI服务日志开始时间
     */
    private String sys_ai_service_log_creat_time;
    /**
     * 系统AI服务日志花费时间
     */
    private String sys_ai_service_log_elapsed_time;
    /**
     * 系统AI服务日志id
     */
    private String sys_ai_service_log_id;
    /**
     * 系统AI服务日志入参内容
     */
    private String sys_ai_service_log_import_context;
    /**
     * 系统AI服务日志出参内容
     */
    private String sys_ai_service_log_outport_context;

}