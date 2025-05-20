//package com.root.websocket.service.impl;
//
//import cn.hutool.core.util.StrUtil;
//import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
//import com.root.entity.HallEntity;
//import com.root.entity.RoomEntity;
//import com.root.mapper.RoomMapper;
//import com.root.websocket.controller.TicTacToeWebSocketController;
//import com.root.websocket.service.GameService;
//import com.root.websocket.service.HallService;
//import com.root.websocket.service.RoomGameService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//
///**
// * @author ：fuyunxiang
// * @date ：Created in 2022年12月14日,0014 21:04
// */
//@Service
//public class RoomGameServiceImpl extends ServiceImpl<RoomMapper, RoomEntity> implements RoomGameService {
//    @Autowired
//    HallService hallService;
//    @Autowired
//    GameService gameService;
//
//    public void endRoom(String id, String code) {
//        RoomEntity roomEntity = this.getById(id);
//        HallEntity hallEntity = hallService.getById(roomEntity.getTableNo());
//
//        roomEntity.setState("3A");
//        this.updateById(roomEntity);
//
//        if (StrUtil.equals(roomEntity.getState(),"1A")){
//            gameService.giveUp(id,code);
//        }
//
//        if (StrUtil.equals(code,"1")){
//            hallEntity.setState("2A");
//
//        }else {
//            hallEntity.setState("2B");
//        }
//
//        hallService.updateById(hallEntity);
//
//    }
//
//    public void check(String id) {
//        RoomEntity roomEntity = this.getById(id);
//        HallEntity hallEntity = hallService.getById(roomEntity.getTableNo());
//        if (StrUtil.equals(roomEntity.getState(), "1A")) {
//            gameService.check(roomEntity.getGameId());
//        } else if (StrUtil.equals(roomEntity.getState(), "2A")) {
//
//            try {
//
//                boolean one = TicTacToeWebSocketController.SendMessage("", hallEntity.getPlayOneSessionId());
//
//                boolean two = TicTacToeWebSocketController.SendMessage("", hallEntity.getPlayOneSessionId());
//                if (!one || !two) {
//                    roomEntity.setState("3A");
//                    this.updateById(roomEntity);
//                    if (!one) {
//                        hallEntity.setState("2A");
//                    } else {
//                        hallEntity.setState("2B");
//                    }
//                    hallService.updateById(hallEntity);
//
//                }
//
//            } catch (Exception e) {
//                System.out.println(e);
//            }
//
//        }
//    }
//
////    开启房间
////            查询房间状态
////    结束房间
////
////
////            对局中
////    等待中
////
////            房间已经销毁
//}
