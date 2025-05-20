package com.root.websocket.service;

import com.root.dto.ReturnMessage;

public interface WebSocketService {
    public void openRoom(String playId, String sessionId) ;
    public void startPlay(String id,String roomId);
    public void checkerboard(String id, String checkerboard ,String playId);
    public void winnner(String id,String roomId, String history,String checkerboard) ;
    public void reStartRoom(String playId,String sessionId);
    public ReturnMessage checkGiveUp(String roomId, String checkid);
}
