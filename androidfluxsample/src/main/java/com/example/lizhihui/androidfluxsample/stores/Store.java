package com.example.lizhihui.androidfluxsample.stores;

import android.util.Log;

import com.example.lizhihui.androidfluxsample.actions.Action;
import com.example.lizhihui.androidfluxsample.rxBus.RxBus;

/**
 * Flux的Store模块
 * 这里使用EventBus来实现Store
 * Created by Lizhihui on 2016/11/12.
 */

public abstract class Store {
    private static final RxBus rxBus = RxBus.getInstance();

    protected Store() {
    }

    void emitStoreChange() {
        Log.i("test", "send");
        this.rxBus.send(changeEvent());
    }

    public abstract StoreChangeEvent changeEvent();
    public abstract void onAction(Action action);

    public class StoreChangeEvent {}
}
