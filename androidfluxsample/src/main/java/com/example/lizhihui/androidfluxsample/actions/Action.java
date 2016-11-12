package com.example.lizhihui.androidfluxsample.actions;

/**
 * Created by Maibenben on 2016/11/12.
 */

public class Action<T> {
    private final String type;
    private final T data;

    Action(String type, T data) {
        this.type = type;
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public T getData() {
        return data;
    }
}
