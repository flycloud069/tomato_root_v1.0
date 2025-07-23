package com.root.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.IsKey;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import lombok.Data;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;

@Data
@TableName("sys_user")
@Table(name = "sys_user")
public class SysUserEntity {
    //actable主键注解
    @Column(name = "sys_user_id", type = MySqlTypeConstant.VARCHAR, comment = "GID", length = 36, isNull = false)
    private String sysUserId;
    @IsKey
    @Column(name = "username", type = MySqlTypeConstant.VARCHAR, comment = "用户名", length = 200, isNull = false)
    private String username;
    @Column(name = "name", type = MySqlTypeConstant.VARCHAR, comment = "用户名称", length = 200, isNull = false)
    private String name;
    @Column(name = "password", type = MySqlTypeConstant.VARCHAR, comment = "用户密码", length = 32, isNull = false)
    private String password;
    @Column(name = "tel", type = MySqlTypeConstant.VARCHAR, comment = "电话号码", length = 255, isNull = true)
    private String tel;
    @Column(name = "email", type = MySqlTypeConstant.VARCHAR, comment = "邮箱", length = 255, isNull = true)
    private String email;
    @Column(name = "personal_profile", type = MySqlTypeConstant.VARCHAR, comment = "个人简介", length = 255, isNull = true)
    private String personal_profile;
    @Column(name = "system_style", type = MySqlTypeConstant.VARCHAR, comment = "系统风格", length = 255, isNull = true)
    private String system_style;
    @Column(name = "basic_color", type = MySqlTypeConstant.VARCHAR, comment = "主题色", length = 255, isNull = true)
    private String basic_color;
    @Column(name = "is_del", type = MySqlTypeConstant.VARCHAR, comment = "是否删除", length = 1, isNull = true)
    private String isDel;
}
