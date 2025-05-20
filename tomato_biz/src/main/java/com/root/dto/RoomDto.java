package com.root.dto;

import lombok.Data;

/**
 * @author ：fuyunxiang
 * @date ：Created in 2022年7月27日,0027 8:15
 */
@Data
public class RoomDto {
    /**
     *游戏房间id
     */
    private String roomId;
    /**
     *游戏局数
     */
    private String gameNumId;

    /**
     *玩家一号id
     */
    private String playId;

    /**
     *玩家二号id
     */
    private String playerId;

    /**
     *玩家一号姓名
     */
    private String playName;

    /**
     *玩家二号姓名
     */
    private String playerName;

    /**
     *最近操作时间
     */
    private String operationTime;


    /**
     *房间开启时间
     */
    private String openTime;


    /**
     *是否准备
     */
    private Boolean isReady;

    /**
     *准备id
     */
    private String ReadyId;

    /**
     *房间作废标志
     */
    private Boolean isDel;

    /**
     *玩家一号sessionId
     */
    private String playSessionId;
    /**
     *玩家二号sessionId
     */
    private String playerSessionId;
    /**
     *先手
     */
    private Boolean firstHand;

}
