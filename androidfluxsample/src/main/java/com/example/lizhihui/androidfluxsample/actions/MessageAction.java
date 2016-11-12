package com.example.lizhihui.androidfluxsample.actions;

/**
 * Created by Lizhihui on 2016/11/12.
 */

public class MessageAction extends Action<String> {
    public static final String ACTION_OLD_MESSAGE = "old_message";
    public static final String ACTION_NEW_MESSAGE = "new_message";

    MessageAction(String type, String data) {
        super(type, data);
    }
}
