package com.bjtu.pcap3.websocket;

import com.bjtu.pcap1.PacketCaptureRunner;
import com.bjtu.security.config.BasedSpringConfigurator;
import org.springframework.stereotype.Component;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@ServerEndpoint(value = "/ws/pcap/tradaverify/websocket",configurator = BasedSpringConfigurator.class)
@Component
public class TrainDataVerify {

    @OnOpen
    public void onOpen(Session session) throws IOException {
        PacketCaptureRunner.addTraDaVerifySession(session);
    }

    @OnClose
    public void onClose(Session session) {
        PacketCaptureRunner.removeTraDaVerifySession(session);
    }

    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

    @OnMessage
    public void onMessage(Session session, String message) {
        System.out.println("Received message: " + message);
        if (message.equals("close")) {
            PacketCaptureRunner.removeTraDaVerifySession(session);
        }
    }
}
