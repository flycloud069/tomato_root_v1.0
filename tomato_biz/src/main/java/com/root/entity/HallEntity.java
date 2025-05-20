//package com.root.entity;
//
//import com.baomidou.mybatisplus.annotation.IdType;
//import com.baomidou.mybatisplus.annotation.TableId;
//import com.baomidou.mybatisplus.annotation.TableName;
//import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
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
// * @date ：Created in 2022年12月14日,0014 21:19
// */
//@Data
//@TableName("hall")
//@Table(name = "hall")
//public class HallEntity {
//    @IsKey
//    @TableId(type=INPUT)
//    @ApiModelProperty(value = "业务编号")
//    @Column(name="table_no",type= MySqlTypeConstant.VARCHAR ,comment = "桌子号" ,length = 100,isNull = false)
//    private String tableNo ;
//    @Column(name="room_id",type= MySqlTypeConstant.VARCHAR ,comment = "房间号" ,length = 100,isNull = true)
//    private String roomId;
//    @Column(name="game_id",type= MySqlTypeConstant.VARCHAR,comment = "对局号" ,length = 100,isNull = true)
//    private String gameId;
//    @Column(name="play_one_id",type= MySqlTypeConstant.VARCHAR,comment = "第一个玩家的id" ,length = 100,isNull = true)
//    private String playOneId;
//    @Column(name="play_two_id",type= MySqlTypeConstant.VARCHAR ,comment = "第二个玩家的id" ,length =100 ,isNull = true)
//    private String playTwoId;
//    @Column(name="play_one_name",type= MySqlTypeConstant.VARCHAR ,comment = "第一个玩家的姓名" ,length = 100,isNull = true)
//    private String playOneName;
//    @Column(name="play_two_name",type= MySqlTypeConstant.VARCHAR ,comment = "第二个玩家的姓名" ,length = 100,isNull = true)
//    private String playTwoName;
//    @Column(name="play_one_session_id",type= MySqlTypeConstant.VARCHAR ,comment = "第一个玩家的网络号" ,length = 100,isNull = true)
//    private String playOneSessionId;
//    @Column(name="play_two_session_id",type= MySqlTypeConstant.VARCHAR ,comment = "第二个玩家的网络号" ,length = 100,isNull = true)
//    private String playTwoSessionId;
//    @Column(name="play_one_in_time",type= MySqlTypeConstant.VARCHAR ,comment = "第一个玩家的进入时间" ,length = 100,isNull = true)
//    private String playOneInTime;
//    @Column(name="play_two_in_time",type= MySqlTypeConstant.VARCHAR ,comment = "第二个玩家的进入时间" ,length = 100,isNull = true)
//    private String playTwoInTime;
//    @Column(name="state",type= MySqlTypeConstant.VARCHAR ,comment = "房间状态" ,length = 100,isNull = true)
//    private String state;
//    @Column(name="state_text",type= MySqlTypeConstant.VARCHAR ,comment = "房间状态" ,length = 100,isNull = true)
//    private String stateText;
//}
