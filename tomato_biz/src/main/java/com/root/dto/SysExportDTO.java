package com.root.dto;

import cn.hutool.json.JSONObject;
import lombok.Data;

@Data
public class SysExportDTO {

    private String fileName;
    /**
     * 系统模版键值
     */
    private String sys_files_value;
    /**
     * Json字符串
     */
    private String jsonObjectStr;

}
