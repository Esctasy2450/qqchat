package cn.esctasy.qqchat.ws;

import cn.esctasy.qqchat.core.chain.Handle;
import cn.esctasy.qqchat.core.chain.impl.EventHandle;
import cn.esctasy.qqchat.core.chain.impl.MessageHandle;
import cn.esctasy.qqchat.core.chain.impl.NoticeHandle;
import cn.esctasy.qqchat.core.chain.impl.RequestHandle;
import cn.esctasy.qqchat.core.bean.escalation.Escalation;
import cn.esctasy.qqchat.core.response.ResponseOperate;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.enums.ReadyState;
import org.java_websocket.handshake.ServerHandshake;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.net.URI;

@Component
@Slf4j
@DependsOn({"WsConfig"})
public class WebSocketConfig extends WebSocketClient {
    private final WsConfig wsConfig;

    /**
     * 责任链实例
     */
    private Handle handle;

    public WebSocketConfig(WsConfig wsConfig) {
        super(URI.create(wsConfig.getSocketPath()), new Draft_6455());
        //初始化配置
        this.wsConfig = wsConfig;
        //连接socket
        this.connect();
        //初始化责任链
        this.initChain();
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

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        //连接成功后重置重试次数
        wsConfig.getRetry().resetting();
        log.info("ws 连接成功");
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
        WebSocketClient client = this;
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                isConnectOpen(client);
            }
        });

        thread.start();
    }

    @Override
    public void onError(Exception ex) {
        log.info("ws 连接错误, {}", ex.getMessage());
    }

    /**
     * 在连接断开时尝试重连
     */
    private synchronized void isConnectOpen(WebSocketClient client) {
        wsConfig.getRetry().add();
        if (!wsConfig.getRetry().isEnable()) {
            log.info("未启用重试，断开连接");
            return;
        }

        if (wsConfig.getRetry().getMax() < wsConfig.getRetry().getRetry()) {
            log.info("重试次数过多，断开连接");
            return;
        }

        try {
            Thread.sleep(wsConfig.getRetry().getInterval());

            log.info("重连服务器，第{}次重试...", wsConfig.getRetry().getRetry());
            if (client.getReadyState().equals(ReadyState.NOT_YET_CONNECTED)) {
                client.connectBlocking();
                return;
            }

            if (client.getReadyState().equals(ReadyState.CLOSING) || client.getReadyState().equals(ReadyState.CLOSED)) {
                client.reconnectBlocking();
            }
        } catch (Exception e) {
            log.error("reconnect error ", e);
        }
    }
}
