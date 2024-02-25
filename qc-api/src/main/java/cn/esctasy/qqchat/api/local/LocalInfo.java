package cn.esctasy.qqchat.api.local;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.UtilityClass;

/**
 * 机器人基本信息
 */
@UtilityClass
public class LocalInfo {

    @Getter
    @Setter
    private static int self_id;
}
