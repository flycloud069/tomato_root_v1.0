package com.root.websocket.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.BooleanUtil;
import cn.hutool.core.util.StrUtil;
import com.root.dto.RoomDto;
import com.root.websocket.service.RoomSrevice;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ：fuyunxiang
 * @date ：Created in 2022年7月27日,0027 8:10
 */
@Service
public class RoomServiceImpl implements RoomSrevice {

    private static List<RoomDto> roomDtoList = new ArrayList<RoomDto>();

    public String addRoom(String playId, String sessionId) {
        int i = roomDtoList.size();

        for (RoomDto roomDto : roomDtoList) {
            if (!BooleanUtil.isTrue(roomDto.getIsDel())) {
                if (StrUtil.equals(playId, roomDto.getPlayId()) || StrUtil.equals(playId, roomDto.getPlayerId())) {
                    return roomDto.getRoomId();
                }
            }
        }
        RoomDto roomDto = new RoomDto();
        if (i != 0) {
            roomDto = roomDtoList.get(i - 1);
            if (BooleanUtil.isTrue(roomDto.getIsDel())){
                roomDto = new RoomDto();
            }
        }
        if (BeanUtil.isEmpty(roomDto.getPlayId())) {
            RoomDto newRoomDto = new RoomDto();
            newRoomDto.setPlayId(playId);
            newRoomDto.setPlaySessionId(sessionId);
            newRoomDto.setIsReady(true);
            newRoomDto.setReadyId(playId);
            newRoomDto.setOpenTime(DateUtil.now());
            newRoomDto.setOperationTime(DateUtil.now());
            newRoomDto.setRoomId(Convert.toStr(i));
            newRoomDto.setGameNumId("0");
            Boolean firsHand = true;

                if (Math.random() > 0.5) {
                    firsHand = false;
                };

            newRoomDto.setFirstHand(firsHand);
            roomDtoList.add(newRoomDto);
            return newRoomDto.getRoomId();
        }

        roomDto.setPlayerId(playId);
        roomDto.setPlayerSessionId(sessionId);
        roomDto.setOperationTime(DateUtil.now());
        roomDtoList.set(i - 1, roomDto);
        return roomDto.getRoomId();
    }

    ;

    public String startPlay(String roomId, String playId) {

        int i = Convert.toInt(roomId);
        RoomDto roomDto = roomDtoList.get(i);
        if (BeanUtil.isEmpty(roomDto.getPlayerId()) || BooleanUtil.isTrue(roomDto.getIsDel())) {
            return "0";
        }
        roomDto.setOperationTime(DateUtil.now());

        int gameNun = Convert.toInt(roomDto.getGameNumId());

        if (!BeanUtil.isEmpty(roomDto.getIsReady()) && BooleanUtil.isTrue(roomDto.getIsReady())) {
            gameNun++;
            roomDto.setIsReady(false);
            roomDto.setReadyId("");
            roomDto.setGameNumId(Convert.toStr(gameNun));
            roomDtoList.set(i, roomDto);
                    return "1";
        }
        roomDto.setIsReady(true);
        roomDto.setReadyId(playId);
        roomDtoList.set(i, roomDto);
        return "2";

    }

    ;

    public void delRoom(String roomId) {
        int i = Convert.toInt(roomId);
        RoomDto roomDto = roomDtoList.get(i);
        roomDto.setIsDel(true);
        roomDto.setOperationTime(DateUtil.now());
        roomDtoList.set(i, roomDto);
        return;
    }

    ;

    public RoomDto getRoom(String roomId) {
        int i = Convert.toInt(roomId);
        RoomDto roomDto = roomDtoList.get(i);
        return roomDto;
    }

    ;
    public String reStartRoom(String playId,String sessionId){
        RoomDto roomDto1 = new RoomDto();
        for (RoomDto roomDto : roomDtoList) {
            if (!BooleanUtil.isTrue(roomDto.getIsDel())) {
                if (StrUtil.equals(playId, roomDto.getPlayId()) || StrUtil.equals(playId, roomDto.getPlayerId())) {
                    roomDto1=  roomDto;
                }
            }
        }
        if (BeanUtil.isEmpty(roomDto1)){
            return addRoom(playId,sessionId);
        }
        roomDto1.setIsDel(true);
        String roomId=roomDto1.getRoomId();
       int i=Convert.toInt(roomId);
        roomDtoList.set(i , roomDto1);
       return addRoom(playId,sessionId);
    };

}
