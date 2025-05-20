package com.root.dto;

import lombok.Data;

@Data
public class SysColumnsDTO {
    /**
     * 数据库名
     */
    private String table_schema;
    /**
     * 表名
     */
    private String table_name;
    /**
     * 列名
     */
    private String column_name;
    /**
     * 列注释
     */
    private String column_comment;
    /**
     * 列序号
     */
    private Long ordinal_position;
    /**
     * 列默认值
     */
    private String column_default;
    /**
     * 是否为空
     */
    private String is_nullable;
    /**
     * 数据类型
     */
    private String data_type;
    /**
     * 数据长度
     */
    private Long character_maximum_length;
    /**
     * 数字类型长度
     */
    private Long numeric_precision;
    /**
     * 数字类型小数点位数
     */
    private Long numeric_scale;
    /**
     * 主键和索引
    * */
    private String column_kye;

}
