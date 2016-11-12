package com.example.lizhihui.androidfluxsample.model;

/**
 * Created by Lizhihui on 2016/11/12.
 */

public class Message {
    private String mText;

    public Message(){}

    public Message(String text) {
        mText = text;
    }

    public String getMessage() {
        return mText;
    }

    public void setMessage(String text) {
        mText = text;
    }
}
