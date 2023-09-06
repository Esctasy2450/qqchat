package cn.esctasy.qqchat.core.chain.impl.message.chat.third;

import cn.esctasy.qqchat.core.chain.impl.message.chat.third.qingyun.QingYunConfig;
import com.pkslow.ai.GoogleBardClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 私聊或群发时，调用第三方api，自动回复
 */
@Component
@RequiredArgsConstructor
public class ThirdChat {
    private final QingYunConfig qingYunConfig;
    private final GoogleBardClient client;

    @Value(("${esctasy.qqchat.third-chat.model}"))
    public String inUse;

    public String getMsg(String msg) {
        if ("qingyun".equals(inUse)) {
            return qingYunConfig.getQingYunMsg(msg);
        }
        if ("bard".equals(inUse)) {
            return client.ask(msg).getChosenAnswer();
        }

        return "请选择需要使用的api";
    }
}
