//package com.root.entity;
//
//
//import com.baomidou.mybatisplus.annotation.TableName;
//import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
//import com.gitee.sunchenbin.mybatis.actable.annotation.IsKey;
//import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
//import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
//import lombok.Data;
//
//@Data
//@TableName("sys_dictionary")
//@Table(name = "sys_dictionary")
//public class SysDictionaryEntity {
//
//                          //actable主键注解
//    @Column(name="dictionary_code",type= MySqlTypeConstant.VARCHAR,comment = "字典编号" ,length = 5,isNull = true)
//    private String dictionaryCode;
//    @IsKey
//    @Column(name="dictionary_name",type= MySqlTypeConstant.VARCHAR,comment = "房间号" ,length = 5,isNull = true)
//    private String dictionaryName;
//
//
//    }
