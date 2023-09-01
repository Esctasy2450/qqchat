package cn.esctasy.qqchat.config;

import cn.esctasy.qqchat.core.chain.Handle;
import cn.esctasy.qqchat.core.chain.impl.EventHandle;
import cn.esctasy.qqchat.core.chain.impl.MessageHandle;
import cn.esctasy.qqchat.core.chain.impl.RequestHandle;
import cn.esctasy.qqchat.core.bean.Escalation;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.net.URI;

@Component
@Slf4j
public class WebSocketConfig {

    private WebSocketClient ws;

    public WebSocketClient getWs() {
        return ws;
    }

    Handle event = new EventHandle();
    Handle message = new MessageHandle();
    Handle request = new RequestHandle();

    {
        event.setNext(message);
        message.setNext(request);
    }

    @Bean
    public WebSocketClient webSocketClient() {
        try {
            WebSocketClient webSocketClient = new WebSocketClient(new URI("ws://localhost:5700"), new Draft_6455()) {
                @Override
                public void onOpen(ServerHandshake handshakedata) {
                    System.out.println("ws 连接成功");
                }

                @Override
                public void onMessage(String message) {
                    if (log.isDebugEnabled()) {
                        log.debug("ws 收到消息: {}", message);
                    }

                    Escalation escalation = JSON.parseObject(message, Escalation.class);
                    event.handling(escalation.getPost_type(), message);
                }

                @Override
                public void onClose(int code, String reason, boolean remote) {
                    System.out.println("ws 退出");
                }

                @Override
                public void onError(Exception ex) {
                    System.out.println("连接错误" + ex.getMessage());
                }
            };
            webSocketClient.connect();
            this.ws = webSocketClient;
            return webSocketClient;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
