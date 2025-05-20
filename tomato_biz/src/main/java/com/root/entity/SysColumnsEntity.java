package com.root.entity;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.annotation.TableName;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.IsKey;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import com.root.dto.SysColumnsDTO;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Map;

@Data
@TableName("sys_columns")
@Table(name = "sys_columns")
public class SysColumnsEntity {
    @IsKey
    @Column(name = "table_schema", type = MySqlTypeConstant.VARCHAR, comment = "数据库名称", length = 255, defaultValue = "", isNull = false)
    private String tableSchema;
    @IsKey
    @Column(name = "table_name", type = MySqlTypeConstant.VARCHAR, comment = "表名称", length = 255, isNull = false)
    private String tableName;
    @IsKey
    @Column(name = "column_name", type = MySqlTypeConstant.VARCHAR, comment = "列名称", length = 255, isNull = false)
    private String columnName;
    @Column(name = "column_comment", type = MySqlTypeConstant.VARCHAR, comment = "列注释", length = 255, isNull = false)
    private String columnComment;
    @Column(name = "ordinal_position", type = MySqlTypeConstant.BIGINT, comment = "列序号", isNull = false)
    private Long ordinalPosition;
    @Column(name = "column_default", type = MySqlTypeConstant.VARCHAR, comment = "列默认值", length = 255, isNull = true)
    private String columnDefault;
    @Column(name = "is_nullable", type = MySqlTypeConstant.VARCHAR, comment = "是否为空", length = 3, isNull = false)
    private String isNullable;
    @Column(name = "data_type", type = MySqlTypeConstant.VARCHAR, comment = "数据类型", length = 255, isNull = false)
    private String dataType;
    @Column(name = "character_maximum_length", type = MySqlTypeConstant.BIGINT, comment = "数据长度", isNull = true)
    private Long characterMaximumLength;
    @Column(name = "numeric_precision", type = MySqlTypeConstant.BIGINT, comment = "数字类型长度", isNull = true)
    private Long numericPrecision;
    @Column(name = "numeric_scale", type = MySqlTypeConstant.BIGINT, comment = "数字类型小数点位数", isNull = true)
    private Long numericScale;
    @Column(name = "column_kye", type = MySqlTypeConstant.VARCHAR, comment = "主键和索引", isNull = true)
    private String columnKye;


    public SysColumnsEntity(String tableSchema, String tableName, String columnName, String columnComment, Long ordinalPosition,
                            String columnDefault, String isNullable, String dataType,
                            Long characterMaximumLength, Long numericPrecision, Long numericScale ,String columnKye) {
        this.tableSchema = tableSchema;
        this.tableName = tableName;
        this.columnName = columnName;
        this.columnComment = columnComment;
        this.ordinalPosition = ordinalPosition;
        this.columnDefault = columnDefault;
        this.isNullable = isNullable;
        this.dataType = dataType;
        this.characterMaximumLength = characterMaximumLength;
        this.numericPrecision = numericPrecision;
        this.numericScale = numericScale;
        this.columnKye = columnKye;

    }

    public SysColumnsEntity(SysColumnsDTO sysColumnsDTO) {
        this.tableSchema = sysColumnsDTO.getTable_schema();
        this.tableName = sysColumnsDTO.getTable_name();
        this.columnName = sysColumnsDTO.getColumn_name();
        this.columnComment = sysColumnsDTO.getColumn_comment();
        this.ordinalPosition = sysColumnsDTO.getOrdinal_position();
        this.columnDefault = sysColumnsDTO.getColumn_default();
        this.isNullable = sysColumnsDTO.getIs_nullable();
        this.dataType = sysColumnsDTO.getData_type();
        this.characterMaximumLength = sysColumnsDTO.getCharacter_maximum_length();
        this.numericPrecision = sysColumnsDTO.getNumeric_precision();
        this.numericScale = sysColumnsDTO.getNumeric_scale();
        this.columnKye = sysColumnsDTO.getColumn_kye();

    }


    public SysColumnsEntity(Object mysqlColumnsObject) {
        Map columnsCollation = (Map) mysqlColumnsObject;
        this.tableSchema = Convert.toStr(columnsCollation.get("TABLE_SCHEMA"));
        this.tableName = Convert.toStr(columnsCollation.get("TABLE_NAME"));
        this.columnName = Convert.toStr(columnsCollation.get("COLUMN_NAME"));
        this.columnComment = Convert.toStr(columnsCollation.get("COLUMN_COMMENT"));
        this.ordinalPosition = Convert.toLong(columnsCollation.get("ORDINAL_POSITION"));
        this.columnDefault = Convert.toStr(columnsCollation.get("COLUMN_DEFAULT"));
        this.isNullable = Convert.toStr(columnsCollation.get("IS_NULLABLE"));
        this.dataType = Convert.toStr(columnsCollation.get("DATA_TYPE"));
        this.characterMaximumLength = Convert.toLong(columnsCollation.get("CHARACTER_MAXIMUM_LENGTH"));
        this.numericPrecision = Convert.toLong(columnsCollation.get("NUMERIC_PRECISION"));
        this.numericScale = Convert.toLong(columnsCollation.get("NUMERIC_SCALE"));
        this.columnKye = Convert.toStr(columnsCollation.get("COLUMN_KEY"));

    }


    @Override
    public boolean equals(Object sysColumnsEntityObject) {
        SysColumnsEntity sysColumnsEntity = (SysColumnsEntity) sysColumnsEntityObject;
        if (StrUtil.equals(this.tableSchema, sysColumnsEntity.getTableSchema()) &&
                StrUtil.equals(this.tableName, sysColumnsEntity.getTableName()) &&
                StrUtil.equals(this.columnName, sysColumnsEntity.getColumnName())) {
            return true;
        } else {
            return false;
        }

    }


}
