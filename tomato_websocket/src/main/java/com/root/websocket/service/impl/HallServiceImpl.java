//package com.root.websocket.service.impl;
//
//import cn.hutool.core.bean.BeanUtil;
//import cn.hutool.core.convert.Convert;
//import cn.hutool.core.date.DateUtil;
//import cn.hutool.core.util.StrUtil;
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
//import com.root.dto.ReturnMessage;
//import com.root.entity.GameEntity;
//import com.root.entity.HallEntity;
//import com.root.entity.RoomEntity;
//import com.root.exceptions.GlobalException;
//import com.root.mapper.HallMapper;
//import com.root.util.ResponseUtil;
//import com.root.websocket.service.GameService;
//import com.root.websocket.service.HallService;
//import com.root.websocket.service.RoomGameService;
//import com.root.websocket.util.RandomUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
///**
// * @author ：fuyunxiang
// * @date ：Created in 2022年12月14日,0014 21:32
// */
//@Service
//public class HallServiceImpl extends ServiceImpl<HallMapper, HallEntity> implements HallService {
//    @Autowired
//    GameService gameService;
//    @Autowired
//    RoomGameService roomGameService;
//
//    public void init() {
//        List<HallEntity> list = this.list();
//        for (HallEntity hallEntity : list) {
//            QueryWrapper<HallEntity> query1 = new QueryWrapper();
//            query1.lambda().eq(HallEntity::getTableNo, hallEntity.getTableNo());
//            boolean b = this.remove(query1);
////         boolean b= this.removeById(hallEntity.getTableNo());
////            System.out.println(b);
//        }
//        for (int i = 1; i <= 100; i++) {
//            HallEntity hallEntity = new HallEntity();
////            Convert.toStr(i);
//            hallEntity.setTableNo(String.format("%03d", i));
//            hallEntity.setState("1A");
//            hallEntity.setStateText("空闲中");
////            System.out.println(i);
//            this.save(hallEntity);
//        }
//
//    }
//
//    public void pair(String id, String name, String sessionid) {
//
//        QueryWrapper<HallEntity> query1 = new QueryWrapper();
//        query1.lambda().like(HallEntity::getState, "2");
//        HallEntity hallEntity = this.getOne(query1);
//        if (BeanUtil.isEmpty(hallEntity)) {
//            QueryWrapper<HallEntity> query2 = new QueryWrapper();
//            query1.lambda().eq(HallEntity::getState, "1A");
//            hallEntity = this.getOne(query2);
//        }
//        if (BeanUtil.isEmpty(hallEntity)) {
//            throw new GlobalException(-1, "房间没有了");
//        }
//        if (StrUtil.equals(hallEntity.getState(), "1A") || StrUtil.equals(hallEntity.getState(), "2A")) {
//            hallEntity.setPlayOneId(id);
//            hallEntity.setPlayOneName(name);
//            hallEntity.setPlayOneInTime(DateUtil.now());
//            hallEntity.setPlayOneSessionId(sessionid);
//        } else {
//            hallEntity.setPlayTwoId(id);
//            hallEntity.setPlayTwoName(name);
//            hallEntity.setPlayTwoInTime(DateUtil.now());
//            hallEntity.setPlayTwoSessionId(sessionid);
//        }
//        if (StrUtil.equals(hallEntity.getState(), "1A")) {
//            hallEntity.setState("2B");
//            hallEntity.setStateText("一号玩家准备就绪");
//        } else if (hallEntity.getState().contains("2")) {
//            hallEntity.setState("3A");
//            hallEntity.setStateText("对局中");
//        }
//
//        //todo
////            开启房间，第一次准备
//        String firsHand = "1";
////            try {
//        if (Math.random() > 0.5) {
//            firsHand = "2";
//        }
//        ;
//
//        String roomId = RandomUtil.getOrderIdByUUId("ROOM");
//        String gameId = RandomUtil.getOrderIdByUUId("GAME");
//        RoomEntity roomEntity = new RoomEntity();
//        roomEntity.setPlayOneId(hallEntity.getPlayOneId());
//        roomEntity.setPlayOneName(hallEntity.getPlayOneName());
//        roomEntity.setPlayOneInTime(hallEntity.getPlayOneInTime());
//        roomEntity.setRoomId(roomId);
//        roomEntity.setGameId(gameId);
//        roomEntity.setState("1A");
//        roomEntity.setPlayTwoId(hallEntity.getPlayTwoId());
//        roomEntity.setPlayTwoInTime(hallEntity.getPlayTwoInTime());
//        roomEntity.setPlayTwoName(hallEntity.getPlayTwoName());
//        roomEntity.setTableNo(hallEntity.getTableNo());
//        roomGameService.save(roomEntity);
//
//        GameEntity gameEntity = new GameEntity();
//        gameEntity.setTwoIsReady("1");
//        gameEntity.setOneIsReady("1");
//        gameEntity.setState("1A");
//        gameEntity.setFirstHand(firsHand);
//        gameEntity.setPlayOneId(hallEntity.getPlayOneId());
//        gameEntity.setPlayOneName(hallEntity.getPlayOneName());
//        gameEntity.setPlayTwoId(hallEntity.getPlayTwoId());
//        gameEntity.setPlayTwoName(hallEntity.getPlayTwoName());
//        gameEntity.setGameId(gameId);
//        gameEntity.setRoomId(roomId);
//        gameEntity.setTableNo(hallEntity.getTableNo());
//        gameService.save(gameEntity);
//
//        hallEntity.setRoomId(roomId);
//        hallEntity.setGameId(gameId);
//        this.updateById(hallEntity);
//
//    }
//
//    public void exitHall(String id, String code) {
//        HallEntity hallEntity = this.getById(id);
//        if (StrUtil.equals(hallEntity.getState(), "2A") || StrUtil.equals(hallEntity.getState(), "2B")) {
//            hallEntity.setState("1A");
//        } else if (StrUtil.equals(hallEntity.getState(), "3A") || StrUtil.equals(hallEntity.getState(), "4A")) {
//            roomGameService.endRoom(hallEntity.getRoomId(), code);
//        }
//
//    }
//
//    public HallEntity getStatr(String id) {
//        QueryWrapper<HallEntity> query1 = new QueryWrapper();
//        query1.lambda().eq(HallEntity::getPlayOneId, id).or().eq(HallEntity::getPlayTwoId, id);
//        HallEntity hallEntity = this.getOne(query1);
//
//        return hallEntity;
//
//    }
//
//
//    public ReturnMessage reconnection(String userId, String sessionId) {
//        QueryWrapper<HallEntity> query1 = new QueryWrapper();
//        query1.lambda().eq(HallEntity::getPlayOneId, userId).or().eq(HallEntity::getPlayTwoId, userId);
////query1.lambda().eq(HallEntity:)
//        HallEntity hallEntity = this.getOne(query1);
//
//        if (BeanUtil.isEmpty(hallEntity)) {
//            return ResponseUtil.fail();
//        }
//        if (StrUtil.equals(hallEntity.getPlayOneId(), userId)) {
//            hallEntity.setPlayOneSessionId(sessionId);
//        } else {
//            hallEntity.setPlayTwoSessionId(sessionId);
//        }
////        RoomEntity roomEntity = roomGameService.getById(hallEntity.getRoomId());
//        GameEntity gameEntity = gameService.getById(hallEntity.getGameId());
//        this.updateById(hallEntity);
//        return ResponseUtil.success(gameEntity);
//        //todo
//        // 发送对局信息
//
//    }
//
//    public int getNum() {
//        QueryWrapper<HallEntity> query1 = new QueryWrapper();
//        query1.lambda().like(HallEntity::getState, "2");
//        List<HallEntity> list = this.list(query1);
//        return list.size();
//    }
////
////    重连
////
////            房间初始化
////    匹配房间
////            离开房间
////    查看房间状态
////            正在匹配的人数
////
////
////    空闲中
////
////            匹配中
////
////    对局中
////            等待中
//
//}
