//package com.root.entity;
//
//import com.baomidou.mybatisplus.annotation.IdType;
//import com.baomidou.mybatisplus.annotation.TableId;
//import com.baomidou.mybatisplus.annotation.TableName;
//import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
//import com.gitee.sunchenbin.mybatis.actable.annotation.IsAutoIncrement;
//import com.gitee.sunchenbin.mybatis.actable.annotation.IsKey;
//import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
//import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
//import io.swagger.annotations.ApiModelProperty;
//import lombok.Data;
//
//import static com.baomidou.mybatisplus.annotation.IdType.INPUT;
//
///**
// * @author ：fuyunxiang
// * @date ：Created in 2022年12月14日,0014 20:35
// */
//@Data
//@TableName("game")
//@Table(name = "game")
//public class GameEntity extends SysBaseEntity {
//
//    @ApiModelProperty(value = "业务编号")
//    @IsKey
//
//    @TableId(type = INPUT)
//    //actable主键注解
//    //自增
//    @Column(name = "game_id", type = MySqlTypeConstant.VARCHAR)
//    private String gameId;
//    @Column(name = "room_id", type = MySqlTypeConstant.VARCHAR, comment = "房间号", length = 100, isNull = true)
//    private String roomId;
//    @Column(name = "table_no", type = MySqlTypeConstant.VARCHAR, comment = "桌子号", length = 100, isNull = true)
//    private String tableNo;
//    @Column(name = "play_one_id", type = MySqlTypeConstant.VARCHAR, comment = "第一个玩家id", length = 100, isNull = true)
//    private String playOneId;
//    @Column(name = "play_two_id", type = MySqlTypeConstant.VARCHAR, comment = "第二个玩家id", length = 300, isNull = true)
//    private String playTwoId;
//    @Column(name = "play_one_name", type = MySqlTypeConstant.VARCHAR, comment = "第一个玩家姓名", length = 100, isNull = true)
//    private String playOneName;
//    @Column(name = "play_two_name", type = MySqlTypeConstant.VARCHAR, comment = "第二个玩家姓名", length = 100, isNull = true)
//    private String playTwoName;
//    @Column(name = "one_is_ready", type = MySqlTypeConstant.VARCHAR, comment = "第一个玩家是否准备了", length = 100, isNull = true)
//    private String oneIsReady;
//    @Column(name = "two_is_ready", type = MySqlTypeConstant.VARCHAR, comment = "第二个玩家是否准备了", length = 100, isNull = true)
//    private String twoIsReady;
//    @Column(name = "first_hand", type = MySqlTypeConstant.VARCHAR, comment = "先手id", length = 100, isNull = true)
//    private String firstHand;
//    @Column(name = "history", type = MySqlTypeConstant.VARCHAR, comment = "对局历史", length = 100, isNull = true)
//    private String history;
//    @Column(name = "escape_id", type = MySqlTypeConstant.VARCHAR, comment = "逃跑id", length = 100, isNull = true)
//    private String escapeId;
//    @Column(name = "winner_id", type = MySqlTypeConstant.VARCHAR, comment = "逃跑id", length = 100, isNull = true)
//    private String winnerId;
//    @Column(name = "loser_id", type = MySqlTypeConstant.VARCHAR, comment = "逃跑id", length = 100, isNull = true)
//    private String loserId;
//    @Column(name = "state", type = MySqlTypeConstant.VARCHAR, comment = "房间状态", length = 100, isNull = true)
//    private String state;
//    @Column(name = "state_text", type = MySqlTypeConstant.VARCHAR, comment = "房间状态", length = 100, isNull = true)
//    private String stateText;
//
//
//}
