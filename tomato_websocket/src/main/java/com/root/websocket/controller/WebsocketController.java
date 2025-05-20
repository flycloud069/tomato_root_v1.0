//package com.root.websocket.controller;
//
//import cn.hutool.core.convert.Convert;
//import cn.hutool.core.util.StrUtil;
//import com.root.dto.ReturnMessage;
//import com.root.entity.HallEntity;
//import com.root.entity.UserEntity;
//import com.root.util.ResponseUtil;
//import com.root.websocket.service.HallService;
//import com.root.websocket.service.WebSocketService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cache.annotation.CachePut;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * @author ：fuyunxiang
// * @date ：Created in 2022年7月25日,0025 19:31
// */
//@RestController
//@RequestMapping("/websocketTest")
//@CrossOrigin
//public class WebsocketController {
//    @Autowired
//    WebSocketService webSocketService;
//    @Autowired
//    HallService hallService;
//
//    //    /**
////     * Put语句适合去保存和更新数据
////     */
////    @CachePut(key = "#userEntity.id")
//    @RequestMapping(value = "/sendAll", method = RequestMethod.POST)
//    public String sendAll() throws Exception {
//        TicTacToeWebSocketController.BroadCastInfo("{\n" +
//                "\t\"hosId\": \"40\",\n" +
//                "\t\"hosName\": \"重庆市武隆区中医院\",\n" +
//                "\t\"hospitalId\": \"\",\n" +
//                "\t\"hospitalName\": \"\",\n" +
//                "\t\"channel_num\": \"0\",\n" +
//                "\t\"health_hos_id\": \"36096\",\n" +
//                "\t\"app_id\": \"23252dabfa7cf25edadd712a314944d9\",\n" +
//                "\t\"app_secret\": \"60ff1ddf9a687639280f13962098c534\",\n" +
//                "\t\"healthTokenUrl\": \"\"\n" +
//                "}");
//        return "ok";
//    }
//
//    @RequestMapping(value = "/sendOne", method = RequestMethod.POST)
//    public String sendOne(String id) throws Exception {
//        TicTacToeWebSocketController.SendMessage("{\n" +
//                "\t\"hosId\": \"40\",\n" +
//                "\t\"hosName\": \"重庆市武隆区中医院\",\n" +
//                "\t\"hospitalId\": \"\",\n" +
//                "\t\"hospitalName\": \"\",\n" +
//                "\t\"channel_num\": \"0\",\n" +
//                "\t\"health_hos_id\": \"36096\",\n" +
//                "\t\"app_id\": \"23252dabfa7cf25edadd712a314944d9\",\n" +
//                "\t\"app_secret\": \"60ff1ddf9a687639280f13962098c534\",\n" +
//                "\t\"healthTokenUrl\": \"\"\n" +
//                "}", id);
//        return "ok";
//    }
//
//    @RequestMapping(value = "/checkGiveUp", method = RequestMethod.POST)
//    public ReturnMessage checkGiveUp(String roomId, String checkid) {
//        return webSocketService.checkGiveUp(roomId, checkid);
//
//    }
//
//    ;
//
//    @RequestMapping(value = "/getOnNum", method = RequestMethod.POST)
//    public ReturnMessage getOnNum() {
//        int i = TicTacToeWebSocketController.getOnNum();
//        Map map = new HashMap();
//        map.put("onNum", Convert.toStr(i));
//        List list = new ArrayList();
//        list.add(map);
//        return ResponseUtil.success(list);
//    }
//
//    @RequestMapping(value = "/getOnPair", method = RequestMethod.POST)
//    public ReturnMessage getOnPair() {
//        int i = hallService.getNum();
//        Map map = new HashMap();
//        map.put("onPair", Convert.toStr(i));
//        List list = new ArrayList();
//        list.add(map);
//        return ResponseUtil.success(list);
//    }
//
//    @RequestMapping(value = "/openRoomFist", method = RequestMethod.POST)
//    public ReturnMessage openRoomFist(String id, String name, String netId) {
//        hallService.pair(id, name, netId);
//        return ResponseUtil.success();
//    }
//
//    @RequestMapping(value = "/getStatr", method = RequestMethod.POST)
//    public ReturnMessage getStatr(String id) {
//       HallEntity hallEntity= hallService.getStatr(id);
//     return ResponseUtil.success(hallEntity);
//    }
//}
