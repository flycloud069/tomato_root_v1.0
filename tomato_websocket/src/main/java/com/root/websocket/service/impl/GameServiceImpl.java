//package com.root.websocket.service.impl;
//
//import cn.hutool.core.convert.Convert;
//import cn.hutool.core.util.StrUtil;
//import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
//import com.root.entity.GameEntity;
//import com.root.entity.HallEntity;
//import com.root.entity.RoomEntity;
//import com.root.mapper.GameMapper;
//import com.root.websocket.controller.TicTacToeWebSocketController;
//import com.root.websocket.service.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
///**
// * @author ：fuyunxiang
// * @date ：Created in 2022年12月14日,0014 21:03
// */
//@Service
//public class GameServiceImpl extends ServiceImpl<GameMapper, GameEntity> implements GameService {
//    @Autowired
//    HallService hallService;
//    @Autowired
//    UserService userService;
//    @Autowired
//    RoomGameService roomSrevice;
//
//    public  void  giveUp(String id,String code){
//
//        GameEntity gameEntity = this.getById(id);
//   winner( gameEntity,code) ;
//    }
//    public void prepare(String id) {
//        GameEntity gameEntity = this.getById(id);
//        if (StrUtil.equals(gameEntity.getPlayOneId(), "")) {
//            gameEntity.setOneIsReady("1");
//        } else {
//            gameEntity.setTwoIsReady("1");
//        }
//        if (StrUtil.equals(gameEntity.getOneIsReady(), "1") && StrUtil.equals(gameEntity.getTwoIsReady(), "1")) {
//            gameEntity.setState("2A");
//        }
//
//    }
//
//    public void next(String history, String player, String id, String code) {
//
//        GameEntity gameEntity = this.getById(id);
//        gameEntity.setHistory(history);
//
//        String[] a = history.split(",");
//        int i = 0;
//        String j = "";
//
//        int[][] k = {{0, 1, 2},
//                {3, 4, 5},
//                {6, 7, 8},
//                {0, 3, 6},
//                {1, 4, 7},
//                {2, 5, 8},
//                {0, 4, 8},
//                {2, 4, 6},};
//
//        for (int[] l : k) {
//            boolean b = true;
//            for (int m : l) {
//                b = StrUtil.equals(a[m], player);
//            }
//            if (b) {
//                winner(gameEntity, code);
//                break;
//            }
//        }
//
//        this.updateById(gameEntity);
//    }
//
//    public void check(String id) {
//        GameEntity gameEntity = this.getById(id);
//        String tableNo = gameEntity.getTableNo();
//        HallEntity hallEntity = hallService.getById(tableNo);
//        try {
//
//
//            boolean one = TicTacToeWebSocketController.SendMessage("", hallEntity.getPlayOneSessionId());
//
//            boolean two = TicTacToeWebSocketController.SendMessage("", hallEntity.getPlayOneSessionId());
//
//            if (!one) {
//                winner(gameEntity, "1");
//
//            } else if (!two) {
//
//                winner(gameEntity, "2");
//
//            }
//        } catch (Exception e) {
//
//        }
//
//    }
//
//    public void winner(GameEntity gameEntity, String code) {
//        HallEntity hallEntity = hallService.getById(gameEntity.getTableNo());
//        RoomEntity roomEntity = roomSrevice.getById(gameEntity.getRoomId());
//        if (StrUtil.equals(code, "1")) {
//
//            userService.changeCode(gameEntity.getPlayOneId(), "-20");
//            userService.changeCode(gameEntity.getPlayTwoId(), "+20");
//        } else {
//            userService.changeCode(gameEntity.getPlayOneId(), "+20");
//            userService.changeCode(gameEntity.getPlayTwoId(), "-20");
//        }
//        gameEntity.setState("3A");
//        this.updateById(gameEntity);
//        roomEntity.setGameNum(Convert.toStr(Convert.toInt(roomEntity.getGameNum()) + 1));
//        roomEntity.setState("3A");
//        roomSrevice.updateById(roomEntity);
//        hallEntity.setState("4A");
//        hallService.updateById(hallEntity);
//
//    }
////
////    准备
////            下棋
////    查看对局状态
////
////
////            对局中
////    对局结束
//
//
//}
