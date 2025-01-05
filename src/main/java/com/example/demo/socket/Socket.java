package com.example.demo.socket;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpSession;

//소켓 메시지 구현
@Component
public class Socket extends TextWebSocketHandler {

    private Map<String, WebSocketSession> sessions = new HashMap<>();
    private ObjectMapper objectMapper = new ObjectMapper();

    // 원하는 유저에게 메세지 전송
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        
        Map<String, Object> messageMap = objectMapper.readValue(message.getPayload(), new TypeReference<Map<String, Object>>(){});
        String sendMessage = messageMap.get("message").toString();
        List<String> userList = (List<String>)messageMap.get("array"); 
        
        for (int i = 0; i < userList.size(); i++) {
            sessions.get(userList.get(i)).sendMessage(new TextMessage(sendMessage));
        }

    }

    // 연결이 성공됐을 떄
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        Map<String, Object> attributes = session.getAttributes();
        HttpSession httpSession = (HttpSession) attributes.get("HTTP_SESSION");
        sessions.put(httpSession.getAttribute("id").toString(), session);
    }

    // 연결이 끊겼을 때
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        Map<String, Object> attributes = session.getAttributes();
        HttpSession httpSession = (HttpSession) attributes.get("HTTP_SESSION");
        sessions.remove(httpSession.getAttribute("id").toString());
    }
}