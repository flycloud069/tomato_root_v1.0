//package com.root.websocket.controller;
//
//import cn.hutool.core.bean.BeanUtil;
//import cn.hutool.core.convert.Convert;
//import cn.hutool.core.util.StrUtil;
//import cn.hutool.json.JSONUtil;
//import com.root.websocket.service.WebSocketService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.annotation.PostConstruct;
//import javax.websocket.*;
//import javax.websocket.server.ServerEndpoint;
//import java.io.IOException;
//import java.util.Map;
//import java.util.concurrent.CopyOnWriteArraySet;
//import java.util.concurrent.atomic.AtomicInteger;
//
///**
// * @Author : cxq * @Date : 2020/8/31 15:50
// */// 前端通过该连接与后端保持交互
//@ServerEndpoint(value = "/TicTacToe")
//@RestController
//public class TicTacToeWebSocketController {
////    @Autowired
////    WebSocketService webSocketService;
//
//    private static WebSocketService webSocketService;
//
//    @Autowired
//    public void setRepository(WebSocketService webSocketService) {
//        TicTacToeWebSocketController.webSocketService = webSocketService;
//    }
//
//
//    @PostConstruct
//    public void init() {
//        System.out.println("websocket 加载");
//
//    }
//
//    private static Logger log = LoggerFactory.getLogger(TicTacToeWebSocketController.class);
//    private static final AtomicInteger OnlineCount = new AtomicInteger(0);
//    // concurrent包的线程安全Set，用来存放每个客户端对应的Session对象。
//    private static CopyOnWriteArraySet SessionSet = new CopyOnWriteArraySet();
//
//    /**
//     * 连接建立成功调用的方法
//     */
//    @OnOpen
//    public void onOpen(Session session) {
//        SessionSet.add(session);
//        int cnt = OnlineCount.incrementAndGet(); // 在线数加
//        log.info("有连接加入，当前连接数为：{}", cnt);
//        SendMessage(session, "{}");
//    }
//
//    /**
//     * 连接关闭调用的方法
//     */
//    @OnClose
//    public void onClose(Session session) {
//        SessionSet.remove(session);
//        int cnt = OnlineCount.decrementAndGet();
//        log.info("有连接关闭，当前连接数为：{}", cnt);
//    }
//
//    /**
//     * 收到客户端消息后调用的方法     *     * @param message     *            客户端发送过来的消息
//     */
//    @OnMessage
//    public void onMessage(String message, Session session) {
//
//
//        log.info("来自客户端的消息：{}", message);
//        Map map1 = JSONUtil.parseObj(message);
//        Map map = (Map) map1.get("msg");
//        String code = Convert.toStr(map1.get("code"));
//        if (StrUtil.equals(code, "20")) {
//            String playId = Convert.toStr(map.get("id"));
//            String sessionId = Convert.toStr(map.get("sessionId"));
//            webSocketService.openRoom(playId, sessionId);
//        } else if (StrUtil.equals(code, "30")) {
//            String playId = Convert.toStr(map.get("id"));
//            String roomId = Convert.toStr(map.get("roomId"));
//            if (BeanUtil.isEmpty(roomId)) {
//                return;
//            }
//            webSocketService.startPlay(playId, roomId);
//        } else if (StrUtil.equals(code, "40")) {
//            String roomId = Convert.toStr(map.get("id"));
//            String checkerboard = Convert.toStr(map.get("checkerboard"));
//            String playId = Convert.toStr(map.get("playId"));
//            webSocketService.checkerboard(roomId, checkerboard, playId);
//        } else if (StrUtil.equals(code, "50")) {
//
//            String playId = Convert.toStr(map.get("id"));
//            String roomId = Convert.toStr(map.get("roomId"));
//            String history = Convert.toStr(map.get("history"));
//            String checkerboard = Convert.toStr(map.get("checkerboard"));
//            webSocketService.winnner(playId, roomId, history, checkerboard);
//
//        } else if (StrUtil.equals(code, "60")) {
//
//            String playId = Convert.toStr(map.get("id"));
//            String sessionId = Convert.toStr(map.get("sessionId"));
//            webSocketService.reStartRoom(playId, sessionId);
//
//        }
////        SendMessage(session, "收到消息，消息内容：" + message);
//    }
//
//    /**
//     * 出现错误     * @param session     * @param error
//     */
//    @OnError
//    public void onError(Session session, Throwable error) {
//        log.error("发生错误：{}，Session ID：{}", error.getMessage(), session.getId());
//        error.printStackTrace();
//    }
//
//    /**
//     * 发送消息，实践表明，每次浏览器刷新，session会发生变化。     * @param session     * @param message
//     */
//    public static void SendMessage(Session session, String message) {
//        try {
//
//            session.getBasicRemote().sendText("{\n" +
//                    "\t\"data\": \"" + session.getId() + "\",\n" +
//                    "\t\"msg\": \"\",\n" +
//                    "\t\"code\": \"10\"\n" +
//                    "}");
//            session.getBasicRemote().sendText(message);
//        } catch (IOException e) {
//            log.error("发送消息出错：{}", e.getMessage());
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 群发消息     * @param message     * @throws IOException
//     */
//    public static void BroadCastInfo(String message) throws IOException {
//        log.info(message);
//        for (Object o : SessionSet) {
//            Session session = (Session) o;
//            if (session.isOpen()) {
//                SendMessage(session, message);
//            }
//        }
//    }
//
//    /**
//     * 指定Session发送消息     * @param sessionId     * @param message     * @throws IOException
//     */
//    public static Boolean SendMessage(String message, String sessionId) throws IOException {
//        Session session = null;
//        for (Object o : SessionSet) {
//
//            Session s = (Session) o;
//            if (s.getId().equals(sessionId)) {
//                session = s;
//                break;
//            }
//        }
//        if (session != null) {
//            SendMessage(session, message);
//        } else {
//            log.warn("没有找到你指定ID的会话：{}", sessionId);
//            return false;
//        }
//        return true;
//    }
//
//    public static int getOnNum() {
//        return SessionSet.size();
//    }
//
//    //clear data
//    public static void clear() {
//
//        for (Object o : SessionSet) {
//            Session session = (Session) o;
//            boolean result = true;
//            try {
//                result = SendMessage("", session.getId());
//            } catch (Exception e) {
//
//            }
//            if (!result) {
//                SessionSet.remove(session);
//
//            }
//        }
//    }
//
//}