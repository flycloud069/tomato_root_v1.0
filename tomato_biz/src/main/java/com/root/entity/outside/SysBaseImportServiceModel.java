package com.root.entity.outside;

import lombok.Data;

/**
 * 系统基础服务主表模型
 */
@Data
public class SysBaseImportServiceModel {
    /**
     * 系统基础服务id
     */
    private String sys_base_service_id;
    /**
     * 系统基础服务入参id
     */
    private String sys_base_import_service_id;
    /**
     * 入参字段
     */
    private String column_name;
}
