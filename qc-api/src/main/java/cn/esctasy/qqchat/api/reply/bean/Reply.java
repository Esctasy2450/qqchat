package cn.esctasy.qqchat.api.reply.bean;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.UUID;

/**
 * 发送消息实体类
 */
@Slf4j
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reply {

    /**
     * 请求路径
     */
    private String action;

    /**
     * 请求参数
     */
    private Map<String, Object> params;

    /**
     * '回声', 如果指定了 echo 字段, 那么响应包也会同时包含一个 echo 字段, 它们会有相同的值
     */
    private String echo;

    /**
     * 组装数据，返回json
     * json是socket请求时使用的数据格式
     */
    public static Reply build(String action, Map<String, Object> params, String echo) {
        return new Reply(action, params, echo);
    }

    public static Reply build(String action, Map<String, Object> params) {
        //echo自动生成
        return new Reply(action, params, UUID.randomUUID().toString().replace("-", ""));
    }

    public void send() {
        String s = JSON.toJSONString(this);
        log.info("发送的信息：{}", s);
        // SpringCglibInterceptor.getBean(WebSocketClient.class).send(s);
    }
}
