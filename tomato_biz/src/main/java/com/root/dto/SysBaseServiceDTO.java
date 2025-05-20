package com.root.dto;

import cn.hutool.json.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

//系统基础服务模块接口
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysBaseServiceDTO {
    /**
     * 系统基础服务键值
     */
    private String sys_base_service_value;
    /**
     * 系统基础服务入参
     */
    private Object  sys_base_service_import_param;

    public  SysBaseServiceDTO getSysBaseServiceDTO(String sys_service_value,JSONObject jsonObject) {
        this.sys_base_service_value=sys_service_value;
        this.sys_base_service_import_param=jsonObject;
        return this;
    }
}
