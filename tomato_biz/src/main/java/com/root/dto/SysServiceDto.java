package com.root.dto;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.Data;

@Data
public class SysServiceDto {
    /**
     * 系统服务键值
     */
    private String sys_service_value;
    /**
     * Json字符串
     */
    private JSONObject jsonObject;


    public  SysServiceDto getSysServiceDto(String sys_service_value,JSONObject jsonObject) {
        this.sys_service_value=sys_service_value;
        this.jsonObject=jsonObject;
        return this;
    }
}
