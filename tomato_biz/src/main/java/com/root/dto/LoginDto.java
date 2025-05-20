package com.root.dto;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import lombok.Data;

import java.util.Date;

@Data
public class LoginDto {
    /**
     *会话id
     */
    private String token;
    /**
     *用户id
     */
    private String sys_user_id;
    /**
     *会话ip
     */
    private String login_ip;
    /**
     *登录时间
     */
    private Date login_datatime;

    /**
     * 初始化登录记录数据守卫
     * @param sys_user_id
     * @param login_ip
     */
    public LoginDto(String sys_user_id,String login_ip){
        this.token=token;
        this.sys_user_id=sys_user_id;
        this.login_ip=login_ip;
       this.login_datatime= DateUtil.date();
    }

    /**
     * 对比登录记录
     * @param loginDto
     * @return
     */
    public int equlLogin(LoginDto loginDto){
        if (StrUtil.equals(loginDto.getSys_user_id(),this.sys_user_id)&& StrUtil.equals(loginDto.getLogin_ip(),this.login_ip)){
            return 1;//同一个ip，同一个用户登录返回同一个token

        }else if(StrUtil.equals(loginDto.getSys_user_id(),this.sys_user_id)&& !StrUtil.equals(loginDto.getLogin_ip(),this.login_ip)){
            return 2; // 不同ip，同一个用户，销毁旧token，并返回新token
        }else {
            return 3; //  新用户 ，返回新token
        }
    }
}
