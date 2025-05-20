package com.root.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.IsKey;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import lombok.Data;

@Data
@TableName("sys_service")
@Table(name = "sys_service")
public class SysServiceEntity {
    //actable主键注解
    @Column(name="sys_service_id",type= MySqlTypeConstant.VARCHAR,comment = "系统服务id" ,length = 36,isNull = true)
    private String sysServiceId;
    @Column(name="sys_service_name",type= MySqlTypeConstant.VARCHAR,comment = "系统服务名称" ,length = 36,isNull = true)
    private String sysServiceName;
    @IsKey
    @Column(name="sys_service_value",type= MySqlTypeConstant.VARCHAR,comment = "系统服务键值" ,length = 36,isNull = true)
    private String sysServiceValue;
    @Column(name="sys_service_sql",type= MySqlTypeConstant.TEXT ,comment = "服务sql"  ,isNull = true)
    private String sysServiceSql;
}
