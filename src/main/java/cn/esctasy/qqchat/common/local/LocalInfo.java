package cn.esctasy.qqchat.common.local;

import lombok.Getter;
import lombok.Setter;

/**
 * 机器人基本信息
 */
public final class LocalInfo {

    private static final LocalInfo info = new LocalInfo();

    private LocalInfo() {}

    @Getter
    @Setter
    private int self_id;

    public static LocalInfo get() {
        return info;
    }
}
