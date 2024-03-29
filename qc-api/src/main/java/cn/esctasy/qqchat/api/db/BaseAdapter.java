package cn.esctasy.qqchat.api.db;

import lombok.Data;

@Data
public class BaseAdapter<T> {
    private T data;
    private Class<T> clazz;

    BaseAdapter() {}

    BaseAdapter(T data) {
        this.data = data;
        this.clazz = (Class<T>) data.getClass();
    }
}
