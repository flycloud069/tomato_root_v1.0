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
// * @date ：Created in 2022年12月14日,0014 20:12
// */
//@Data
//@TableName("room")
//@Table(name = "room")
//public class RoomEntity extends SysBaseEntity {
//
//
//    @ApiModelProperty(value = "业务编号")
//    @IsKey
//    @TableId(type=INPUT) //actable主键注解
//    @Column(name="room_id",type= MySqlTypeConstant.VARCHAR ,comment = "房间号" ,length = 100,isNull = false)
//    private String roomId;
//    @Column(name = "table_no", type = MySqlTypeConstant.VARCHAR, comment = "桌子号", length = 100, isNull = false)
//    private String tableNo;
//    @Column(name = "game_id", type = MySqlTypeConstant.VARCHAR, comment = "对局号", length = 100, isNull = true)
//    private String gameId;
//    @Column(name = "game_num", type = MySqlTypeConstant.VARCHAR, comment = "对局数", length = 100, isNull = true)
//    private String gameNum;
//    @Column(name = "play_one_id", type = MySqlTypeConstant.VARCHAR, comment = "第一个玩家的id", length = 100, isNull = true)
//    private String playOneId;
//    @Column(name = "play_two_id", type = MySqlTypeConstant.VARCHAR, comment = "第二个玩家的id", length = 300, isNull = true)
//    private String playTwoId;
//    @Column(name = "play_one_name", type = MySqlTypeConstant.VARCHAR, comment = "第一个玩家的姓名", length = 100, isNull = true)
//    private String playOneName;
//    @Column(name = "play_two_name", type = MySqlTypeConstant.VARCHAR, comment = "第二个玩家的姓名", length = 100, isNull = true)
//    private String playTwoName;
//    @Column(name = "play_one_in_time", type = MySqlTypeConstant.VARCHAR, comment = "第一个玩家的进入时间", length = 100, isNull = true)
//    private String playOneInTime;
//    @Column(name = "play_two_in_time", type = MySqlTypeConstant.VARCHAR, comment = "第二个玩家的进入时间", length = 100, isNull = true)
//    private String playTwoInTime;
//    @Column(name = "state", type = MySqlTypeConstant.VARCHAR, comment = "房间状态", length = 100, isNull = true)
//    private String state;
//    @Column(name = "state_text", type = MySqlTypeConstant.VARCHAR, comment = "房间状态", length = 100, isNull = true)
//    private String stateText;
//
//}
