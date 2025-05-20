package com.root.dto;

import lombok.Data;

@Data
public class UserDto {
    /**
     *用户密码
     */
    private String password;
    /**
     *用户名
     */
    private String username;
}
