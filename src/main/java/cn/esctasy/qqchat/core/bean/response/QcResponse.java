package cn.esctasy.qqchat.core.bean.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class QcResponse {
    public static final String OK = "ok";

    /**
     * 状态, 表示 API 是否调用成功
     * ok  api调用成功
     * async  api调用已经提交异步处理, 此时 retcode 为 1, 具体 api 调用是否成功无法得知
     * failed  api调用失败
     */
    private String status;

    /**
     * 0 调用成功
     * 1 已提交异步处理
     * 其他 操作失败, 具体原因可以看响应的 msg 和 wording 字段
     */
    private int retcode;

    /**
     * 错误消息, 仅在 API 调用失败时有该字段
     */
    private String msg;

    /**
     * 对错误的详细解释(中文), 仅在 API 调用失败时有该字段
     */
    private String wording;

    /**
     * 数据
     */
    private Map<String, Object> data;

    /**
     * '回声', 如果请求时指定了 echo, 那么响应也会包含 echo
     */
    private String echo;
}
