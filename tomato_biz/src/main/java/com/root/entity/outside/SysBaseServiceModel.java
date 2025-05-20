package com.root.entity.outside;

import lombok.Data;

/**
 * 系统基础服务主表模型
 */
@Data
public class SysBaseServiceModel {
    /**
     * 系统基础服务id
     */
    private String sys_base_service_id;
    /**
     * 系统基础服务名称
     */
    private String sys_base_service_name;
    /**
     * 系统基础服务键值
     */
    private String sys_base_service_value;
    /**
     * 系统基础服务类型
     */
    private String sys_base_service_type;
    /**
     * 系统基础服务源表名称
     */
    private String table_name;
}
