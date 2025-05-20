package com.root.entity;


import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.IsKey;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import lombok.Data;


import java.sql.Timestamp;
import java.util.Map;


@Data
@TableName("sys_table")
@Table(name = "sys_table")
public class SysTableEntity {
    @IsKey
    @Column(name = "table_schema", type = MySqlTypeConstant.VARCHAR, comment = "数据库名称", length = 255, defaultValue = "", isNull = false)
    private String tableSchema;
    @TableId(value = "table_name", type = IdType.INPUT)
    @IsKey
    @Column(name = "table_name", type = MySqlTypeConstant.VARCHAR, comment = "表名称", length = 255, defaultValue = "", isNull = false)
    private String tableName;
    @Column(name = "table_comment", type = MySqlTypeConstant.VARCHAR, comment = "表注释", length = 255, defaultValue = "", isNull = false)
    private String tableComment;
    @Column(name = "engine", type = MySqlTypeConstant.VARCHAR, comment = "表引擎", length = 255, defaultValue = "", isNull = false)
    private String engine;
    @Column(name = "table_collation", type = MySqlTypeConstant.VARCHAR, comment = "表字符集", length = 255, defaultValue = "", isNull = false)
    private String tableCollation;

    public SysTableEntity(String tableSchema, String tableName, String tableComment,
                          String engine,String tableCollation) {
        this.tableSchema = tableSchema;
        this.tableName = tableName;
        this.tableComment = tableComment;
        this.engine = engine;
        this.tableCollation = tableCollation;


    }



    public SysTableEntity(Object mysqlTableObject) {
        Map tableCollation = (Map) mysqlTableObject;
        this.tableSchema = Convert.toStr( tableCollation.get("TABLE_SCHEMA"));
        this.tableName = Convert.toStr( tableCollation.get("TABLE_NAME"));
        this.tableComment = Convert.toStr( tableCollation.get("TABLE_COMMENT"));
        this.engine = Convert.toStr( tableCollation.get("ENGINE"));
        this.tableCollation = Convert.toStr( tableCollation.get("TABLE_COLLATION"));


    }
}
