package com.bjtu.pcap1.websocket;

import com.bjtu.pcap1.PacketCaptureRunner;
import com.bjtu.security.config.BasedSpringConfigurator;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/ws/pcap/c2lact/websocket",configurator = BasedSpringConfigurator.class)
@Component
public class C2lact {
    @OnOpen
    public void onOpen(Session session) {
        PacketCaptureRunner.addC2lactSession(session);
    }

    @OnClose
    public void onClose(Session session) {
        PacketCaptureRunner.removeC2lactSession(session);
    }

    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }
    @OnMessage
    public void onMessage(Session session, String message) {
        System.out.println("Received message: " + message);
        if(message.equals("close")) {
            PacketCaptureRunner.removeC2lactSession(session);
        }
    }
}