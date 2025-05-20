package com.root.entity.outside;

import lombok.Data;

@Data
public class SysExtractServiceModel {
    /**
     * 系统抽取服务组信息表id
     */
    private String sys_extract_service_id;
    /**
     * 系统抽取服务组信息表name
     */
    private String sys_extract_service_name;
    /**
     * 系统抽取服务组信息表value
     */
    private String sys_extract_service_value;
    /**
     * 系统动态方法管理表id
     */
    private String sys_dynamic_method_id;
    /**
     * 系统解析列表关联系统存储服务id
     */
    private String sys_json_parse_relate_sys_storage_service_id;
}
