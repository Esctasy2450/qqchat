package cn.esctasy.qqchat.core.common.utils;

public class StrUtil {
    public static String join(String... strings) {
        if (strings.length < 1) {
            return "";
        }

        StringBuilder builder = new StringBuilder();
        for (String s : strings) {
            builder.append(s);
        }

        return builder.toString();
    }
}
