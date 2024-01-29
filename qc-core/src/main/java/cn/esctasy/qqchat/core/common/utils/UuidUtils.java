package cn.esctasy.qqchat.core.common.utils;

import java.util.UUID;

public class UuidUtils {
    public static String id() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
