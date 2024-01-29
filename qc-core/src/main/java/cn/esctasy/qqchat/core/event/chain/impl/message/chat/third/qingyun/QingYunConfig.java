package cn.esctasy.qqchat.core.event.chain.impl.message.chat.third.qingyun;

import cn.esctasy.qqchat.core.common.utils.HttpUtil;
import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 青云客聊天api
 */
@Component
@ConfigurationProperties(prefix = QingYunConfig.prefix)
public class QingYunConfig {
    public static final String prefix = "esctasy.qqchat.third-chat.qingyun";

    /**
     * 地址
     */
    @Getter
    @Setter
    private String api;

    /**
     * 青云客api调用
     */
    public String getQingYunMsg(String message) {
        String res = HttpUtil.sendGet(api + "?key=free&appid=0&msg=" + message);
        QingYun q = JSON.parseObject(res, QingYun.class);
        return q.getContent();
    }

    @Getter
    @Setter
    private static class QingYun {
        private String result;
        private String content;
    }
}
