package com.example.lizhihui.androidfluxsample.actions;

import com.example.lizhihui.androidfluxsample.dispatcher.Dispatcher;

/**
 * Flux的ActionCreator模块
 * Created by Lizhihiui on 2016/11/12..
 */
public class ActionCreator {

    private static ActionCreator mInstance;
    final Dispatcher dispatcher;

    private ActionCreator(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    public static ActionCreator getInstance(Dispatcher dispatcher) {
        if (mInstance == null) {
            mInstance = new ActionCreator(dispatcher);
        }
        return mInstance;
    }

    public void sendMessage(String message) {
        dispatcher.dispatch(new MessageAction(MessageAction.ACTION_NEW_MESSAGE, message));
    }
}
