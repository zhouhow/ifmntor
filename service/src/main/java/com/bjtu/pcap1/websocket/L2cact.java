package com.bjtu.pcap1.websocket;

import com.bjtu.pcap1.PacketCaptureRunner;
import com.bjtu.security.config.BasedSpringConfigurator;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/ws/pcap/l2cact/websocket",configurator = BasedSpringConfigurator.class)
@Component
public class L2cact {
    @OnOpen
    public void onOpen(Session session) {
        PacketCaptureRunner.addL2cactSession(session);
    }

    @OnClose
    public void onClose(Session session) {
        PacketCaptureRunner.removeL2cactSession(session);
    }

    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

    @OnMessage
    public void onMessage(Session session, String message) {
        System.out.println("Received message: " + message);
        if(message.equals("close")) {
            PacketCaptureRunner.removeL2cactSession(session);
        }
    }
}