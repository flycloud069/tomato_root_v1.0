package com.root.websocket.service;

import com.root.dto.RoomDto;

/**
 * @author ：fuyunxiang
 * @date ：Created in 2022年7月27日,0027 8:08
 */

public interface RoomSrevice  {
    public String addRoom(String playId,String sessionId) ;
    public String startPlay(String roomId,String playId);
    public void delRoom(String roomId);
    public RoomDto getRoom(String roomId) ;
    public String reStartRoom(String playId,String sessionId);
//    public String isGiveUp(String roomId);
}
