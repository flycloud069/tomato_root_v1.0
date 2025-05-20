package com.root.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.IsKey;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import lombok.Data;

@Data
@TableName("sys_service_param")
@Table(name = "sys_service_param")
public class SysServiceParamEntity {

    //actable主键注解
    @Column(name="sys_service_param_id",type= MySqlTypeConstant.CHAR,comment = "系统服务参数id" ,length = 36,isNull = true)
    private String sysServiceParamId;
    @IsKey
    @Column(name="sys_service_id",type= MySqlTypeConstant.VARCHAR,comment = "系统服务id" ,length = 36,isNull = true)
    private String sysServiceId;
    @Column(name="sys_service_param_name",type= MySqlTypeConstant.VARCHAR,comment = "系统服务参数名称" ,length = 36,isNull = true)
    private String sysServiceParamName;
    @IsKey
    @Column(name="sys_service_param_value",type= MySqlTypeConstant.VARCHAR ,comment = "系统服务参数键值"  ,isNull = true)
    private String sysServiceParamValue;
    @Column(name="sys_service_param_isnull",type= MySqlTypeConstant.VARCHAR ,comment = "系统服务参数是否为空"  ,isNull = true)
    private String sysServiceParamIsnull;

}
