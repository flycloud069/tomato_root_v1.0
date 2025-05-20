package com.root.dto;

import lombok.Data;

@Data
public class SysStorageDTO {
    /**
     * 系统json解析服务关联系统存储服务id
     */
    private String sys_json_parse_relate_sys_storage_service_id;
    /**
     * 系统功能测试json字符串
     */
    private String putJson;
}
