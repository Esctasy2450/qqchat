package cn.esctasy.qqchat.core.socket;

import cn.esctasy.qqchat.core.common.chain.BaseChain;
import cn.esctasy.qqchat.core.common.utils.ChainKeyWords;
import cn.esctasy.qqchat.core.config.WsConfig;
import cn.esctasy.qqchat.core.event.chain.Handle;
import cn.esctasy.qqchat.core.event.response.ResponseOperate;
import lombok.extern.slf4j.Slf4j;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.enums.ReadyState;
import org.java_websocket.handshake.ServerHandshake;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import java.net.URI;

@Component
@Slf4j
@DependsOn({"WsConfig"})
public class WebSocketConfig extends WebSocketClient {
    private final WsConfig wsConfig;

    /**
     * 责任链实例
     */
    private final Handle handle = new BaseChain().initChain();

    public WebSocketConfig(WsConfig wsConfig) {
        super(URI.create(wsConfig.getSocketPath()), new Draft_6455());
        // 初始化配置
        this.wsConfig = wsConfig;
        // 连接socket
        this.connect();
    }

    @Override
    public void onOpen(ServerHandshake handShake) {
        // 连接成功后重置重试次数
        wsConfig.getRetry().resetting();
        log.info("[socket] ws 连接成功");
    }

    @Override
    public void onMessage(String message) {
        if (log.isDebugEnabled()) {
            log.debug("[event] ws 收到消息: {}", message.trim());
        }

        if (message.contains(ChainKeyWords.getPt())) {
            // 上报数据走责任链
            handle.handling(message.trim());
            return;
        }

        // 处理响应数据
        ResponseOperate.handleResponse(message.trim());
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        WebSocketClient client = this;
        new Thread(() -> isConnectOpen(client)).start();
    }

    @Override
    public void onError(Exception ex) {
        log.warn("[socket] ws 连接错误, {}", ex.getMessage());
    }

    /**
     * 在连接断开时尝试重连
     */
    private synchronized void isConnectOpen(WebSocketClient client) {
        wsConfig.getRetry().add();
        if (!wsConfig.getRetry().isEnable()) {
            log.warn("[socket] 未启用重试，连接已断开");
            return;
        }

        if (wsConfig.getRetry().getMax() < wsConfig.getRetry().getRetry()) {
            log.warn("[socket] 重试次数过多，连接已断开");
            return;
        }

        try {
            Thread.sleep(wsConfig.getRetry().getInterval());

            log.info("[socket] 重连服务器，第{}次重试...", wsConfig.getRetry().getRetry());
            if (client.getReadyState().equals(ReadyState.NOT_YET_CONNECTED)) {
                client.connectBlocking();
                return;
            }

            if (client.getReadyState().equals(ReadyState.CLOSING) || client.getReadyState().equals(ReadyState.CLOSED)) {
                client.reconnectBlocking();
            }
        } catch (Exception e) {
            log.error("[socket] reconnect error ", e);
        }
    }
}
