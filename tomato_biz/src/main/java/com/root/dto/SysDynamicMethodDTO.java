package com.root.dto;

import lombok.Data;

@Data
public class SysDynamicMethodDTO {
    /**
     * 系统动态方法管理表id
     */
    private String sys_dynamic_method_id;
    /**
     * 系统动态方法入参
     */
    private String inputString;
}
