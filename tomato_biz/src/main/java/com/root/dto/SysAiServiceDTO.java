package com.root.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//系统AI服务模块接口
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysAiServiceDTO {
    /**
     * 系统ai服务键值
     */
    private String sys_ai_service_value;
    /**
     * 系统ai服务入参
     */
    private Object  sys_ai_service_import_param;
}
