package com.root.entity.outside;

import lombok.Data;

@Data
public class SysBaseOutputServiceModel {
    /**
     * 系统基础服务id
     */
    private String sys_base_output_service_id;
    /**
     * 系统基础服务出参id
     */
    private String sys_base_service_id;
    /**
     * 出参字段
     */
    private String column_name;
}
