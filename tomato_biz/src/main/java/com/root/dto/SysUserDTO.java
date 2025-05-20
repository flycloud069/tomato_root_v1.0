package com.root.dto;

import lombok.Data;

@Data
public class SysUserDTO {
    /**
     * 用户登录名称
     */
    private String username;
    /**
     * 昵称
     */
    private String name;
    /**
     * 密码
     */
    private String password;

    /**
     * 电话号码
     */
    private String tel;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 系统风格
     */
    private String system_style;
    /**
     * 主题色
     */
    private String basic_color;

}
