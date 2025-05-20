package com.root.entity.outside;

import lombok.Data;

@Data
public class SysDynamicMethodModel {
    /**
     * 系统动态方法管理表id
     */
    private String sys_dynamic_method_id;
    /**
     * 系统动态方法name
     */
    private String sys_dynamic_method_name;
    /**
     * 系统动态方法value
     */
    private String sys_dynamic_method_value;
    /**
     * 系统动态方法代码内容
     */
    private String sys_dynamic_method_code_context;
    /**
     * 系统动态方法测试内容
     */
    private String sys_dynamic_method_test_context;
}
