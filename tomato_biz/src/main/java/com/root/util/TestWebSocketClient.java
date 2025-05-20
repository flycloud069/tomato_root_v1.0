package com.root.util;


import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.handshake.ServerHandshake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;

public class TestWebSocketClient extends WebSocketClient {

    private final Logger LOGGER = LoggerFactory.getLogger(TestWebSocketClient.class);

    public TestWebSocketClient(URI serverUri) {
        super(serverUri);
    }

    public TestWebSocketClient(URI serverUri, Draft protocolDraft) {
        super(serverUri, protocolDraft);
    }

    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        LOGGER.info("Open a WebSocket connection on client. ");
    }

    @Override
    public void onClose(int arg0, String arg1, boolean arg2) {
        LOGGER.info("Close a WebSocket connection on client. ");
    }

    @Override
    public void onMessage(String msg) {
        LOGGER.info("WebSocketClient receives a message: " + msg);
    }

    @Override
    public void onError(Exception exception) {
        LOGGER.error("WebSocketClient exception. ", exception);
    }

}