package cn.esctasy.qqchat.core.chain.impl.message;

import cn.esctasy.qqchat.common.third.QingYunConfig;
import cn.esctasy.qqchat.common.utils.SpringContextHolder;
import cn.esctasy.qqchat.common.ws.WsExample;
import cn.esctasy.qqchat.core.bean.reply.Reply;
import cn.esctasy.qqchat.core.chain.Handle;
import cn.esctasy.qqchat.core.bean.escalation.message.PrivateEs;
import com.alibaba.fastjson.JSON;
import lombok.Data;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PrivateHandle extends Handle {
    private static final CloseableHttpClient httpclient = HttpClients.createDefault();

    @Override
    public void handling(String code, String metadata) {
        if (!"private".equals(code)) {
            this.goNext(code, metadata);
            return;
        }

        PrivateEs privateEs = JSON.parseObject(metadata, PrivateEs.class);

        String message = getQingYunMsg(privateEs);

        Map<String, Object> param = new HashMap<>();
        param.put("user_id", privateEs.getUser_id());
        param.put("message", message);
        param.put("auto_escape", true);
        WsExample.getWs().send(Reply.build("send_private_msg", param, "test"));
    }

    /**
     * 青云客api调用
     */
    private static String getQingYunMsg(PrivateEs privateEs) {
        String res = sendGet(SpringContextHolder.getBean(QingYunConfig.class).getApi() + "?key=free&appid=0&msg=" + privateEs.getRaw_message());
        QingYun q = JSON.parseObject(res, QingYun.class);
        return q.getContent();
    }


    @Data
    private static class QingYun {
        private String result;
        private String content;
    }

    /**
     * 发送HttpGet请求
     *
     * @param url
     * @return
     */
    public static String sendGet(String url) {
        HttpGet httpget = new HttpGet(url);
        CloseableHttpResponse response = null;
        try {
            response = httpclient.execute(httpget);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        String result = null;
        try {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                result = EntityUtils.toString(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
