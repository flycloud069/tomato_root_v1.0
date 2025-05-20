//package com.root.websocket.service.impl;
//
//import cn.hutool.core.convert.Convert;
//import cn.hutool.core.util.BooleanUtil;
//import cn.hutool.core.util.StrUtil;
//import cn.hutool.json.JSONObject;
//import com.root.dto.ReturnMessage;
//import com.root.dto.RoomDto;
//import com.root.entity.TicTacToeEntity;
//import com.root.mapper.TicTacToeMapper;
//import com.root.util.ResponseUtil;
//import com.root.websocket.controller.TicTacToeWebSocketController;
//import com.root.websocket.service.RoomSrevice;
//import com.root.websocket.service.UserService;
//import com.root.websocket.service.WebSocketService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.Resource;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * @author ：fuyunxiang
// * @date ：Created in 2022年7月27日,0027 9:00
// */
//@Service
//public class WebSocketServiceImpl implements WebSocketService {
//    @Autowired
//    RoomSrevice roomSrevice;
//    @Resource
//    TicTacToeMapper ticTacToeMapper;
//    @Resource
//    UserService userService;
//
//
//    public void openRoom(String playId, String sessionId) {
//        String roomId = roomSrevice.addRoom(playId, sessionId);
//        String gameNum = roomSrevice.startPlay(roomId, playId);
//        if (StrUtil.equals("1", gameNum)) {
//            Map map = new HashMap();
//            map.put("roomId", roomId);
//
//            RoomDto roomDto = roomSrevice.getRoom(roomId);
//
//            map.put("playId", roomDto.getPlayId());
//            map.put("playerId", roomDto.getPlayerId());
//            map.put("FirstHand", roomDto.getFirstHand());
//            sengJson(map, "20", roomDto.getPlaySessionId());
//            sengJson(map, "20", roomDto.getPlayerSessionId());
//            return;
//        }
//
//        return;
//
//    }
//
//    ;
//
//    public void startPlay(String playId, String roomId) {
//        String gameNum = roomSrevice.startPlay(roomId, playId);
//
//        if (StrUtil.equals("0", gameNum)) {
//
//            return;
//        } else if (StrUtil.equals("2", gameNum)) {
//            RoomDto roomDto = roomSrevice.getRoom(roomId);
//            Map map = new HashMap();
//            if (StrUtil.equals(roomDto.getPlayerId(), playId)) {
//                sengJson(map, "70", roomDto.getPlaySessionId());
//            } else {
//                sengJson(map, "70", roomDto.getPlayerSessionId());
//            }
//            return;
//        } else {
//            Map map = new HashMap();
//            map.put("roomId", roomId);
//            RoomDto roomDto = roomSrevice.getRoom(roomId);
//            map.put("gameNum", roomDto.getGameNumId());
//            map.put("FirstHand", roomDto.getFirstHand());
//            map.put("playId", roomDto.getPlayId());
//            sengJson(map, "30", roomDto.getPlaySessionId());
//            sengJson(map, "30", roomDto.getPlayerSessionId());
//            return;
//        }
//
//    }
//
//    ;
//
//    public void checkerboard(String roomId, String checkerboard, String playId) {
//        Map map = new HashMap();
//
//        map.put("checkerboard", checkerboard);
//        map.put("playId", playId);
////        map.put("ct", "sdaf");
//        RoomDto roomDto = roomSrevice.getRoom(roomId);
//        Boolean sengdStatue = sengJson(map, "40", roomDto.getPlaySessionId());
//        Boolean sengderStatue = sengJson(map, "40", roomDto.getPlayerSessionId());
//        isGiveUp(roomDto, sengdStatue, sengderStatue);
//        return;
//    }
//
//    ;
//
//    public void winnner(String id, String roomId, String history, String checkerboard) {
//        RoomDto roomDto = roomSrevice.getRoom(roomId);
//        TicTacToeEntity ticTacToeEntity = new TicTacToeEntity();
//        ticTacToeEntity.setHistory(history);
//        ticTacToeEntity.setPlayersId(roomDto.getPlayId() + "," + roomDto.getPlayerId());
//        ticTacToeEntity.setGameNum(roomDto.getGameNumId());
//        ticTacToeEntity.setWinner(id);
//        ticTacToeMapper.insert(ticTacToeEntity);
//        userService.changeCode(id, "20");
//        String loseId = "";
//        if (StrUtil.equals(id, roomDto.getPlayId())) {
//            loseId = roomDto.getPlayerId();
//        } else {
//            loseId = roomDto.getPlayId();
//        }
//        String winnerCode = userService.changeCode(id, "20");
//        String losserCode = userService.changeCode(loseId, "-20");
//
//        Map map = new HashMap();
//        map.put("winner", id);
//        map.put("checkerboard", checkerboard);
//        map.put("winnerCode", winnerCode);
//        map.put("losserCode", losserCode);
//
//        Boolean sengdStatue = sengJson(map, "50", roomDto.getPlaySessionId());
//        Boolean sengderStatue = sengJson(map, "50", roomDto.getPlayerSessionId());
//
//
//    }
//
//
//    ;
//
//    public void reStartRoom(String playId, String sessionId) {
//        String roomId = roomSrevice.reStartRoom(playId, sessionId);
//        String gameNum = roomSrevice.startPlay(roomId, playId);
//        if (StrUtil.equals("1", gameNum)) {
//            Map map = new HashMap();
//            map.put("roomId", roomId);
//
//            RoomDto roomDto = roomSrevice.getRoom(roomId);
//
//            map.put("playId", roomDto.getPlayId());
//            map.put("playerId", roomDto.getPlayerId());
//            map.put("FirstHand", roomDto.getFirstHand());
//            sengJson(map, "60", roomDto.getPlaySessionId());
//            sengJson(map, "60", roomDto.getPlayerSessionId());
//            return;
//        }
//
//        return;
//    }
//
//    ;
//
//    public ReturnMessage checkGiveUp(String roomId, String checkid) {
////        Boolean result =true;
//        RoomDto roomDto = roomSrevice.getRoom(roomId);
//        if (BooleanUtil.isTrue(roomDto.getIsDel())) {
//            return ResponseUtil.success();
//        }
//
//        String checkSession = "";
//        String openid = "";
//        if (StrUtil.equals(checkid, roomDto.getPlayId())) {
//            checkSession = roomDto.getPlaySessionId();
//            openid = roomDto.getPlayerId();
//        } else {
//            checkSession = roomDto.getPlayerSessionId();
//            openid = roomDto.getPlayId();
//        }
//        Map map = new HashMap();
//        Boolean checkResult = sengJson(map, "60", checkSession);
//        if (BooleanUtil.isFalse(checkResult)) {
//            userService.changeCode(openid, "20");
//            userService.changeCode(openid, "-20");
//            roomSrevice.delRoom(roomId);
//            return ResponseUtil.fail();
//        }
//        return ResponseUtil.success();
//
//
//    }
//
//    ;
//
//    private static Boolean sengJson(Object o, String code, String id) {
//        Boolean result = true;
//        Map map1 = new JSONObject(o);
//
//        Map map = new HashMap();
//        map.put("code", code);
//        map.put("msg", map1);
//        JSONObject objectMap = new JSONObject(map);
//        String jsonMap = Convert.toStr(objectMap);
//
//        try {
//            result = TicTacToeWebSocketController.SendMessage(jsonMap, id);
//        } catch (Exception e) {
//            return result;
//        }
//        return result;
//
//    }
//
//    private void isGiveUp(RoomDto roomDto, Boolean sengdStatue, Boolean sengderStatue) {
////        String escapeId = "0";
//        if (!sengdStatue) {
//            Map map1 = new HashMap();
//            String escapeId = roomDto.getPlayId();
//            String saveId = roomDto.getPlayerId();
//            userService.changeCode(escapeId, "-20");
//            String newCode = userService.changeCode(saveId, "20");
//            map1.put("ticTacToeRade", newCode);
//            sengJson(map1, "80", roomDto.getPlayerSessionId());
//
//        }
//        if (!sengderStatue) {
//            Map map1 = new HashMap();
//            String escapeId = roomDto.getPlayerId();
//            String saveId = roomDto.getPlayId();
//            userService.changeCode(escapeId, "-20");
//            String newCode = userService.changeCode(saveId, "20");
//            map1.put("ticTacToeRade", newCode);
//            sengJson(map1, "80", roomDto.getPlaySessionId());
//        }
//    }
//
//
//}
