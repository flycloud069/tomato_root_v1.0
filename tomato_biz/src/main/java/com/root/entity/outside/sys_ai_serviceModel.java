package com.root.entity.outside;
import lombok.Data;

@Data
public class sys_ai_serviceModel {
    /**
     * 系统AI服务提示词
     */
    private String sys_ai_callword;
    /**
     * 系统AI服务回复最大Token数
     */
    private String sys_ai_max_tokens;
    /**
     * 系统AI服务id
     */
    private String sys_ai_service_id;
    /**
     * 系统AI服务名称
     */
    private String sys_ai_service_name;
    /**
     * 系统Ai服务测试内容
     */
    private String sys_ai_service_test;
    /**
     * 系统AI服务键值
     */
    private String sys_ai_service_value;
    /**
     * 系统AI服务取样阀值
     */
    private String sys_ai_temperature;
    /**
     * 系统AI服务取样随机值
     */
    private String sys_ai_top_k;

}