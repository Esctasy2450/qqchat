package cn.esctasy.qqchat.core.common.utils;

public class ChainKeyWords {
    private static final String PT = "post_type";
    private static final String PT_EVENT = "meta_event";
    private static final String PT_ET = "meta_event_type";
    private static final String PT_ET_LIFE_CYCLE = "lifecycle";
    private static final String PT_ET_HEART_BEAT = "heartbeat";
    private static final String PT_MESSAGE = "message";
    private static final String PT_MT = "message_type";
    private static final String PT_MT_PRIVATE = "private";
    private static final String PT_MT_GROUP = "group";
    private static final String PT_NOTICE = "notice";
    private static final String PT_NT = "notice_type";
    private static final String PT_NT_FRIEND_ADD = "friend_add";
    private static final String PT_NT_NOTIFY = "notify";
    private static final String PT_NT_ST = "sub_type";
    private static final String PT_NT_ST_POKE = "poke";
    private static final String PT_REQUEST = "request";
    private static final String PT_RT = "request_type";
    private static final String PT_RT_FRIEND = "friend";

    public static String getPt() {
        return join(PT);
    }

    public static String getPtEvent() {
        return join(PT, PT_EVENT);
    }

    public static String getPtEtHeartBeat() {
        return join(PT_ET, PT_ET_HEART_BEAT);
    }

    public static String getPtEtLifeCycle() {
        return join(PT_ET, PT_ET_LIFE_CYCLE);
    }

    public static String getPtMessage() {
        return join(PT, PT_MESSAGE);
    }

    public static String getPtMtPrivate() {
        return join(PT_MT, PT_MT_PRIVATE);
    }

    public static String getPtMtGroup() {
        return join(PT_MT, PT_MT_GROUP);
    }

    public static String getPtNotice() {
        return join(PT, PT_NOTICE);
    }

    public static String getPtNtFriendAdd() {
        return join(PT_NT, PT_NT_FRIEND_ADD);
    }

    public static String getPtNtNotify() {
        return join(PT_NT, PT_NT_NOTIFY);
    }

    public static String getPtNtStPoke() {
        return join(PT_NT_ST, PT_NT_ST_POKE);
    }

    public static String getPtRequest() {
        return join(PT, PT_REQUEST);
    }

    public static String getPtRtFriend() {
        return join(PT_RT, PT_RT_FRIEND);
    }

    /**
     * 拼接字符串
     * key，value -> "key":"value"
     */
    private static String join(String key, String value) {
        return StrUtil.join("\"" + key, "\":\"", value + "\"");
    }

    /**
     * 拼接字符串
     * key -> "key":"
     */
    private static String join(String key) {
        return StrUtil.join("\"" + key, "\":\"");
    }
}
