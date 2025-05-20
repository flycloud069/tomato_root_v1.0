//package com.root.entity;
//
//import com.baomidou.mybatisplus.annotation.TableName;
//import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
//import com.gitee.sunchenbin.mybatis.actable.annotation.IsKey;
//import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
//import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
//import lombok.Data;
//
//@Data
//@TableName("sys_name")
//@Table(name = "sys_name")
//public class SysNameEntity {
//    @IsKey                         //actable主键注解
//    @Column(name="sys_name_id",type= MySqlTypeConstant.CHAR,comment = "系统名称关联id" ,length = 36,isNull = true)
//    private String sysNameId;
//    @Column(name="sys_name_code",type= MySqlTypeConstant.CHAR ,comment = "系统名称id" ,length =36 ,isNull = true)
//    private String sysNameCode;
//    @Column(name="sys_name_value",type= MySqlTypeConstant.VARCHAR ,comment = "系统名称值" ,length =225 ,isNull = true)
//    private String sysNameValue;
//    @Column(name="sys_name_type",type= MySqlTypeConstant.CHAR ,comment = "系统名称类别（一般为国籍环境）" ,length =36 ,isNull = true)
//    private String sysNameType;
//}
