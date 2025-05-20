package com.root.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.IsKey;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import lombok.Data;

@Data
@TableName("sys_menu_vue")
@Table(name = "sys_menu_vue")
public class SysMenuVueEntity {
    @IsKey                         //actable主键注解
    @Column(name="sys_menu_id",type= MySqlTypeConstant.CHAR,comment = "菜单id" ,length = 36,isNull = true)
    private String dictionaryCode;
    @Column(name="component",type= MySqlTypeConstant.VARCHAR ,comment = "vue对象名" ,length =255 ,isNull = true)
    private String component;
    @IsKey
    @Column(name="redirect",type= MySqlTypeConstant.VARCHAR,comment = "重定向" ,length = 255,isNull = false)
    private String redirect;
    @Column(name="hidden",type= MySqlTypeConstant.TINYINT,comment = "控制路由和子路由是否显示在 sidebar" ,length = 1,isNull = false)
    private String hidden;
    @Column(name="hideChildrenInMenu",type= MySqlTypeConstant.TINYINT ,comment = "控制子菜单不显示" ,length =1,isNull = false)
    private String hideChildrenInMenu;
    @Column(name="keepAlive",type= MySqlTypeConstant.TINYINT,length =1,comment = "开启缓存"  ,isNull = false)
    private String keepAlive;
    @Column(name="permission",type= MySqlTypeConstant.VARCHAR,comment = "功能标识"  ,length =255,isNull = true)
    private String permission;
    @Column(name="title",type= MySqlTypeConstant.VARCHAR,comment = "面包屑，或者标题栏"  ,length =255,isNull = true)
    private String title;
}
