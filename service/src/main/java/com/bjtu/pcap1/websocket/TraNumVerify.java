package com.bjtu.pcap1.websocket;

import com.bjtu.pcap1.PacketCaptureRunner;
import com.bjtu.security.config.BasedSpringConfigurator;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.*;

@ServerEndpoint(value = "/ws/pcap/tranumverify/websocket",configurator = BasedSpringConfigurator.class)
@Component
public class TraNumVerify {
    private static final Set<Session> sessions = Collections.synchronizedSet(new HashSet<>());

    @OnOpen
    public void onOpen(Session session) throws IOException {
        PacketCaptureRunner.addTrainNumberVerifySession(session);
        sessions.add(session);
    }

    @OnClose
    public void onClose(Session session) {
        PacketCaptureRunner.removeTrainNumberVerifySession(session);
        sessions.remove(session);
    }

    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

//    @Scheduled(fixedDelay = 1000)
//    public void send() {
//        synchronized (sessions) {
//            for (Session session : sessions) {
//                try {
//                    session.getBasicRemote().sendText(objectMapper.writeValueAsString(traNumVerifyService.getById(1)));
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        }
//    }

    @OnMessage
    public void onMessage(Session session, String message) {
        System.out.println("Received message: " + message);
        if(message.equals("close")) {
            sessions.remove(session);
            PacketCaptureRunner.removeTrainNumberVerifySession(session);
            System.out.println("Remain session numbers: " + sessions.size());
        }
    }
}