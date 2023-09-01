package cn.esctasy.qqchat.core.chain.impl.message;

import cn.esctasy.qqchat.core.chain.Handle;
import cn.esctasy.qqchat.common.utils.SpringContextHolder;
import cn.esctasy.qqchat.config.WebSocketConfig;
import cn.esctasy.qqchat.core.bean.message.PrivateEs;
import com.alibaba.fastjson.JSON;
import lombok.Data;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;


import java.io.IOException;
import java.util.Map;

public class PrivateHandle extends Handle {
    private static final CloseableHttpClient httpclient = HttpClients.createDefault();

    @Override
    public void handling(String code, String metadata) {
        if ("private".equals(code)) {
            PrivateEs privateEs = JSON.parseObject(metadata, PrivateEs.class);
            String res = sendGet("http://api.qingyunke.com/api.php?key=free&appid=0&msg=" + privateEs.getRaw_message());
            QingYun q = JSON.parseObject(res, QingYun.class);
            WebSocketConfig webSocketConfig = SpringContextHolder.getBean(WebSocketConfig.class);
            webSocketConfig.getWs().send("{\n" +
                    "    \"action\": \"send_private_msg\",\n" +
                    "    \"params\": {\n" +
                    "        \"user_id\": " + privateEs.getUser_id() + ",\n" +
                    "        \"message\": \"" + q.getContent() + "\",\n" +
                    "        \"auto_escape\": true\n" +
                    "    },\n" +
                    "    \"echo\": \"'回声', 如果指定了 echo 字段, 那么响应包也会同时包含一个 echo 字段, 它们会有相同的值\"\n" +
                    "}");
            return;
        }

        this.goNext(code, metadata, this.getClass().getName());
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


    /**
     * 发送HttpGet带参请求
     *
     * @param url
     * @param header
     * @return
     */
    public static String sendGet(String url, Map<String, String> header) {
        HttpGet httpGet = new HttpGet(url);


        //设置头部
        for (Map.Entry entry : header.entrySet()) {

            httpGet.setHeader(entry.getKey().toString(), entry.getValue().toString());
        }


        CloseableHttpResponse response = null;
        try {
            response = httpclient.execute(httpGet);
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
