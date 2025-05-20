package com.root.dto;

import cn.hutool.json.JSONObject;
import lombok.Data;

import java.awt.*;
import java.util.List;

@Data
public class SysServiceInsertDTO {
    /**
     * 服务名称
     */
    private String sysServiceName;
    /**
     * 服务键值
     */
    private String sysServiceValue;
    /**
     * 服务sql
     */
    private String sysServiceSql;

}
