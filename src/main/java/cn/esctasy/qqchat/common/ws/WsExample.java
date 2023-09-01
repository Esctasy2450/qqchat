package cn.esctasy.qqchat.common.ws;


import lombok.Getter;
import lombok.Setter;
import org.java_websocket.client.WebSocketClient;

public class WsExample {

    @Getter
    @Setter
    private static WebSocketClient ws;
}
