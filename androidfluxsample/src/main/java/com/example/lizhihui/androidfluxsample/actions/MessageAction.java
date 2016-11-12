package com.example.lizhihui.androidfluxsample.actions;

/**
 * Created by Maibenben on 2016/11/12.
 */

public class MessageAction extends Action<String> {
    public static final String ACTION_NEW_MESSAGE = "new_message";

    MessageAction(String type, String data) {
        super(type, data);
    }
}
