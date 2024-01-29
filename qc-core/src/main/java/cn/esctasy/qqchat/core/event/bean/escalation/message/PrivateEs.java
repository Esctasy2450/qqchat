package cn.esctasy.qqchat.core.event.bean.escalation.message;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class PrivateEs {
    /**
     * postType
     */
    @JSONField(name = "post_type")
    private String postType;
    /**
     * messageType
     */
    @JSONField(name = "message_type")
    private String messageType;
    /**
     * time
     */
    @JSONField(name = "time")
    private Integer time;
    /**
     * selfId
     */
    @JSONField(name = "self_id")
    private Integer selfId;
    /**
     * subType
     */
    @JSONField(name = "sub_type")
    private String subType;
    /**
     * messageId
     */
    @JSONField(name = "message_id")
    private Integer messageId;
    /**
     * userId
     */
    @JSONField(name = "user_id")
    private Integer userId;
    /**
     * targetId
     */
    @JSONField(name = "target_id")
    private Integer targetId;
    /**
     * message
     */
    @JSONField(name = "message")
    private String message;

    /**
     * 消息内容
     */
    @JSONField(name = "raw_message")
    private String rawMessage;
    /**
     * font
     */
    @JSONField(name = "font")
    private Integer font;
    /**
     * sender
     */
    @JSONField(name = "sender")
    private SenderDTO sender;

    /**
     * SenderDTO
     */
    @NoArgsConstructor
    @Data
    public static class SenderDTO {
        /**
         * age
         */
        @JSONField(name = "age")
        private Integer age;
        /**
         * nickname
         */
        @JSONField(name = "nickname")
        private String nickname;
        /**
         * sex
         */
        @JSONField(name = "sex")
        private String sex;
        /**
         * userId
         */
        @JSONField(name = "user_id")
        private Integer userId;
    }
}
