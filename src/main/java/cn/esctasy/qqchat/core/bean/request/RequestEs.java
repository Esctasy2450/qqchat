package cn.esctasy.qqchat.core.bean.request;

import lombok.Data;

@Data
public class RequestEs {

    /**
     * 请求类型
     * friend	好友请求
     * group	群请求
     */
    private String request_type;
}
