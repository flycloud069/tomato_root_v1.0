package com.root.dto;

import com.root.entity.outside.SysJsonParseModel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class JsonParseDTO {
    /**
     * 字段名称
     */
    private String fieldName;
    /**
     * 目标字段长度
     */
    private String targetLength;
    /**
     * 目标字段类型
     */
    private String targetFieldType;
    /**
     * 目标字段名称
     */
    private String targetFieldName;
    /**
     * 字段值
     */
    private String fieldValue;
    /**
     * 目标字段值
     */
    private String targetValue;
    /**
     * 子串
     */
    private String sonString;
    /**
     * 子解析对象
     */
    private List<JsonParseDTO> sonParseS;

    /**
     * 子解析结果
     */
    private List<List<JsonParseDTO>> sonList;

    public JsonParseDTO (){
    }
    /***
     * 正常初始化
     * @param fieldName
     * @param targetLength
     * @param targetFieldName
     * @param targetFieldType
     */
    public JsonParseDTO(String fieldName, String targetLength, String targetFieldName, String targetFieldType) {
        this.fieldName = fieldName;
        this.targetLength = targetLength;
        this.targetFieldName = targetFieldName;
        this.targetFieldType = targetFieldType;

    }
    /***
     * jsonPase解析对象转工作对象
     */
    public JsonParseDTO(SysJsonParseModel sysJsonParseModel) {
        this.fieldName = sysJsonParseModel.getField_name();
        this.targetLength = sysJsonParseModel.getTarget_length();
        this.targetFieldName = sysJsonParseModel.getTarget_field_name();
        this.targetFieldType = sysJsonParseModel.getTarget_field_type();
    }

    /***
     * List对象初始化
     * @param fieldName
     * @param targetLength
     * @param targetFieldName
     * @param targetFieldType
     * @param sonParseS
     */
    public JsonParseDTO(String fieldName, String targetLength, String targetFieldName, String targetFieldType, List<JsonParseDTO> sonParseS) {
        this.fieldName = fieldName;
        this.targetLength = targetLength;
        this.targetFieldName = targetFieldName;
        this.targetFieldType = targetFieldType;
        this.sonParseS = sonParseS;
    }


}
