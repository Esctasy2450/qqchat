package cn.esctasy.qqchat.ws;

import cn.esctasy.qqchat.core.chain.Handle;
import cn.esctasy.qqchat.core.chain.impl.EventHandle;
import cn.esctasy.qqchat.core.chain.impl.MessageHandle;
import cn.esctasy.qqchat.core.chain.impl.NoticeHandle;
import cn.esctasy.qqchat.core.chain.impl.RequestHandle;
import cn.esctasy.qqchat.core.bean.escalation.Escalation;
import cn.esctasy.qqchat.core.response.ResponseOperate;
import com.alibaba.fastjson.JSON;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.net.URI;

@Component
@Slf4j
@RequiredArgsConstructor
public class WebSocketConfig {

    @Value("${esctasy.qqchat.ws.socket-path}")
    public String socketPath;

    /**
     * 责任链实例
     */
    Handle handle;

    {
        this.initChain();
    }

    @Bean
    public WebSocketClient webSocketClient() {
        try {
            WebSocketClient webSocketClient = new WebSocketClient(new URI(socketPath), new Draft_6455()) {
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
                    if (StringUtils.hasText(escalation.getEcho())) {
                        //响应数据直接处理
                        ResponseOperate.handleResponse(message);
                        return;
                    }

                    //上报数据走责任链
                    handle.handling(escalation.getPost_type(), message);
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

            return webSocketClient;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 初始化责任链
     */
    private void initChain() {
        //事件调用
        handle = new EventHandle();

        //增加消息调用
        Handle message = new MessageHandle();
        handle.setNext(message);

        //增加上报请求调用
        Handle request = new RequestHandle();
        message.setNext(request);

        //增加通知调用
        Handle notice = new NoticeHandle();
        request.setNext(notice);
    }
}
