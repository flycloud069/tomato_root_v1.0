package com.root.dto;

import lombok.Data;

@Data
public class SysExtractServiceDTO {
    /**
     * 系统抽取方法id
     */
    private String sys_extract_service_id;
    /**
     * 系统抽取方法入参
     */
    private String inputString;
}
