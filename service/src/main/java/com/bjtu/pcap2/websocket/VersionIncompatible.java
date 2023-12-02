package com.bjtu.pcap2.websocket;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.bjtu.pcap1.PacketCaptureRunner;
import com.bjtu.security.config.BasedSpringConfigurator;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;

@ServerEndpoint(value = "/ws/pcap/verincomp/websocket",configurator = BasedSpringConfigurator.class)
@Component
public class VersionIncompatible {
    @OnOpen
    public void onOpen(Session session) throws IOException {
        PacketCaptureRunner.addVerIncompSession(session);
    }

    @OnClose
    public void onClose(Session session) {
        PacketCaptureRunner.removeVerIncompSession(session);
    }

    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

    @OnMessage
    public void onMessage(Session session, String message) {
        System.out.println("Received message: " + message);
        if (message.equals("close")) {
            PacketCaptureRunner.removeVerIncompSession(session);
        }
    }
}
