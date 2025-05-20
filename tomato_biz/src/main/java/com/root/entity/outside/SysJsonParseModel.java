package com.root.entity.outside;

import com.root.dto.JsonParseDTO;
import lombok.Data;

import java.util.List;
import java.util.Map;
@Data
public class SysJsonParseModel {
    /**
     * 字段解析父id
     */
    private String sys_json_parse_pid;
    /**
     * 字段解析id
     */
    private String sys_json_parse_id;
    /**
     * 系统json解析服务id
     */
    private String sys_json_parse_service_id;
    /**
     * 字段名称
     */
    private String field_name;
    /**
     * 目标字段长度
     */
    private String target_length;
    /**
     * 目标字段类型
     */
    private String target_field_type;
    /**
     * 目标字段名称
     */
    private String target_field_name;
}
