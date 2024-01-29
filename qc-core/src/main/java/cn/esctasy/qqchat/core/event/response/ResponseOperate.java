package cn.esctasy.qqchat.core.event.response;

import cn.esctasy.qqchat.core.event.bean.response.QcResponse;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ResponseOperate {
    /**
     * 处理响应数据
     */
    public static void handleResponse(String metadata) {
        QcResponse response = JSON.parseObject(metadata, QcResponse.class);
        if (QcResponse.OK.equals(response.getStatus())) {
            log.info("echo:[{}]调用成功", response.getEcho());
        } else {
            log.info(metadata);
        }
    }
}
