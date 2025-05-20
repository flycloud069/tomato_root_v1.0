package com.root.entity.outside;
import lombok.Data;

@Data
public class sys_extracts_serviceModel {
    /**
     * 系统动态方法管理表id
     */
    private String sys_dynamic_method_id;
    /**
     * 系统抽取服务信息表id
     */
    private String sys_extracts_service_id;
    /**
     * 系统抽取服务信息表name
     */
    private String sys_extracts_service_name;
    /**
     * 系统抽取服务信息表推荐横并发
     */
    private String sys_extracts_service_recommend_across_concurrence_number;
    /**
     * 系统抽取服务信息表推荐接口间隔
     */
    private String sys_extracts_service_recommend_interface_time_interval;
    /**
     * 系统抽取服务信息表推荐纵并发数
     */
    private String sys_extracts_service_recommend_vertical_concurrence_number;
    /**
     * 系统抽取服务信息表测试内容
     */
    private String sys_extracts_service_test_context;
    /**
     * 系统抽取服务信息表推荐时间段间隔
     */
    private String sys_extracts_service_time_range_interval;
    /**
     * 系统抽取服务信息表value
     */
    private String sys_extracts_service_value;

}