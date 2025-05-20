//package com.root.entity;
//
//import com.baomidou.mybatisplus.annotation.IdType;
//import com.baomidou.mybatisplus.annotation.TableId;
//import com.baomidou.mybatisplus.annotation.TableName;
//import com.baomidou.mybatisplus.core.mapper.BaseMapper;
//import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
//import com.gitee.sunchenbin.mybatis.actable.annotation.IsAutoIncrement;
//import com.gitee.sunchenbin.mybatis.actable.annotation.IsKey;
//import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
//import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
//import io.swagger.annotations.ApiModelProperty;
//import lombok.Data;
//
///**
// * @author ：fuyunxiang
// * @date ：Created in 2022年7月25日,0025 18:23
// */
//@Data
//@TableName("tic_tac_toe")
//@Table(name = "tic_tac_toe")
//public class TicTacToeEntity extends SysBaseEntity{
//
//    @TableId(type = IdType.AUTO)
//    @ApiModelProperty(value = "业务编号")
//    @IsKey                         //actable主键注解
//    @IsAutoIncrement             //自增
//    @Column(name="id",type= MySqlTypeConstant.INT)
//    private String id;
//    @Column(name="room_id",type= MySqlTypeConstant.VARCHAR,comment = "房间号" ,length = 100,isNull = true)
//    private String roomId;
//    @Column(name="game_num",type= MySqlTypeConstant.VARCHAR,comment = "局号" ,length = 100,isNull = true)
//    private String gameNum;
//    @Column(name="history",type= MySqlTypeConstant.VARCHAR ,comment = "历史记录" ,length =300 ,isNull = true)
//    private String history;
//    @Column(name="winner",type= MySqlTypeConstant.VARCHAR ,comment = "赢家" ,length = 100,isNull = true)
//    private String winner;
//    @Column(name="record",type= MySqlTypeConstant.VARCHAR ,comment = "操作记录" ,length = 100,isNull = true)
//    private String record;
//    @Column(name="players_id",type= MySqlTypeConstant.VARCHAR ,comment = "玩家们的id" ,length = 100,isNull = true)
//    private String playersId;
//    @Column(name="players_name",type= MySqlTypeConstant.VARCHAR ,comment = "玩家们的姓名" ,length = 100,isNull = true)
//    private String playersName;
//}
