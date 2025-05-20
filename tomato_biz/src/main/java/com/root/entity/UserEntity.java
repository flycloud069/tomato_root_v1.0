//package com.root.entity;
//
//
//import com.baomidou.mybatisplus.annotation.IdType;
//import com.baomidou.mybatisplus.annotation.TableId;
//import com.baomidou.mybatisplus.annotation.TableName;
//import com.gitee.sunchenbin.mybatis.actable.annotation.*;
//import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
//import io.swagger.annotations.ApiModelProperty;
//import lombok.Data;
//
//@Data
//@TableName("user")
//@Table(name = "user")
//
//public class UserEntity extends SysBaseEntity {
//
//    @TableId(type = IdType.AUTO)
//    @ApiModelProperty(value = "业务编号")
//    @IsKey                         //actable主键注解
//    @IsAutoIncrement             //自增
//    @Column(name = "id", type = MySqlTypeConstant.INT)
//    private String id;
//    @Index(value = "t_idx_name", columns = "name")
//    @Column(name = "name", type = MySqlTypeConstant.VARCHAR, comment = "名字", length = 100, isNull = false)
//    private String name;
//    @Column(name = "password", type = MySqlTypeConstant.VARCHAR, comment = "密码", length = 100, isNull = false)
//    private String password;
//    @Column(name = "mail_no", type = MySqlTypeConstant.VARCHAR, comment = "邮件号", length = 100, isNull = false)
//    private String mailNo;
//    @Column(name = "tic_tac_toe_rade", type = MySqlTypeConstant.VARCHAR, comment = "井字棋分数", defaultValue = "0", length = 100, isNull = false)
//    private String ticTacToeRade;
//    @Column(name = "session_id", type = MySqlTypeConstant.VARCHAR, comment = "井字棋分数", defaultValue = "0", length = 100, isNull = false)
//    private String sessionId;
//
//
//}
