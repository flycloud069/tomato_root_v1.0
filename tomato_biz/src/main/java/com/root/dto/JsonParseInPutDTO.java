package com.root.dto;

import lombok.Data;

@Data
public class JsonParseInPutDTO {
    /***
     * 被解析json字符串
     */
    private String sys_json_parse_service_test_context;
    /***
     * 系统json解析服务id
     */
    private  String sys_json_parse_service_id;
}
