package com.example.demo.socket;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class Socket extends TextWebSocketHandler {

    private List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

    // 클라이언트로부터 메시지가 도착했을 때 호출되는 메소드
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        System.err.println("메세지 전송");
        System.err.println("세션접속수 " + sessions.size());
        System.err.println("메세지 " + message.getPayload());
        System.err.println("");

        for (int i = 0; i < sessions.size(); i++) {
            if (session.isOpen()) {
                sessions.get(i).sendMessage(new TextMessage(message.getPayload()));
            }

        }
    }

    // 연결이 성공됐을 떄
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);
        System.err.println("연결성공 됐을 떄");
        System.err.println(session);
        System.err.println("");
    }

    // 연결이 끊겼을 때
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session);
        System.err.println("연결 끊겼을 때");
        System.err.println(session);
        System.err.println("");
    }
}