package com.example.lizhihui.androidfluxsample.stores;

import android.util.Log;

import com.example.lizhihui.androidfluxsample.actions.Action;
import com.example.lizhihui.androidfluxsample.actions.MessageAction;
import com.example.lizhihui.androidfluxsample.model.Message;

/**
 * Created by Lizhihui on 2016/11/12.
 */

public class MessageStore extends Store {
    private static MessageStore singleton;
    private Message mMessage = new Message();

    public MessageStore() {
        super();
    }

    public String getMessage() {
        return mMessage.getMessage();
    }

    @Override
    public void onAction(Action action) {
        switch (action.getType()) {
            case MessageAction.ACTION_OLD_MESSAGE:
                Log.i("test", "ACTION_OLD_MESSAGE");
                mMessage.setMessage((String) action.getData());
                break;
            case MessageAction.ACTION_NEW_MESSAGE:
                Log.i("test", "ACTION_NEW_MESSAGE");
                mMessage.setMessage((String) action.getData());
                break;
            default:
        }
        emitStoreChange();
    }


    @Override
    public StoreChangeEvent changeEvent() {
        return new StoreChangeEvent();
    }
}
