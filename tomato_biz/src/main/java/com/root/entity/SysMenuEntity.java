package com.root.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.IsKey;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import lombok.Data;

@Data
@TableName("sys_menu")
@Table(name = "sys_menu")
public class SysMenuEntity {
    @IsKey                         //actable主键注解
    @Column(name="sys_menu_id",type= MySqlTypeConstant.CHAR,comment = "菜单id" ,length = 36,isNull = true)
    private String dictionaryCode;
    @Column(name="menu_title_id",type= MySqlTypeConstant.CHAR ,comment = "菜单标题id" ,length =36 ,isNull = false)
    private String menuTitleId;
    @IsKey
    @Column(name="parent_menu_id",type= MySqlTypeConstant.CHAR,comment = "父级菜单id" ,length = 36,isNull = true)
    private String parentMenuId;
    @Column(name="menu_page",type= MySqlTypeConstant.VARCHAR,comment = "菜单页面" ,length = 255,isNull = false)
    private String menuPage;
    @Column(name="menu_icon",type= MySqlTypeConstant.VARCHAR ,comment = "菜单图标" ,length =255,isNull = false)
    private String menuIcon;
    @Column(name="menu_xh",type= MySqlTypeConstant.TINYINT,comment = "序号"  ,isNull = true)
    private String menuXh;
}
