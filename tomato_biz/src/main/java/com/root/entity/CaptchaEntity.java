package com.root.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.IsAutoIncrement;
import com.gitee.sunchenbin.mybatis.actable.annotation.IsKey;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author ：fuyunxiang
 * @date ：Created in 2022年12月14日,0014 17:38
 */
@Data
@TableName("captcha")
@Table(name = "captcha")
public class CaptchaEntity {
    @TableId(type = IdType.INPUT)
//    @ApiModelProperty(value = "业务编号")
//                     //actable主键注解
//    @IsAutoIncrement             //自增
//    @Column(name="id",type= MySqlTypeConstant.INT)
//    private String id;
    @IsKey
    @Column(name="uuid",type= MySqlTypeConstant.VARCHAR,comment = "uuid" ,length = 100,isNull = true)
    private String uuid;
    @Column(name="code",type= MySqlTypeConstant.VARCHAR,comment = "验证码" ,length = 100,isNull = true)
    private String code;
    @Column(name="expire_time",type= MySqlTypeConstant.DATETIME,comment = "时间" ,length = 100,isNull = true)
    private Date expireTime;
}
