package com.example.lizhihui.androidfluxsample.dispatcher;

import com.example.lizhihui.androidfluxsample.actions.Action;
import com.example.lizhihui.androidfluxsample.stores.Store;

import java.util.ArrayList;
import java.util.List;

/**
 * Flux的Dispatcher模块
 * Created by Lizhihui on 2016/11/12.
 */
public class Dispatcher {
    private static Dispatcher mInstance;
    private final List<Store> stores = new ArrayList<>();

    private Dispatcher() {

    }

    public static Dispatcher getInstance() {
        if (mInstance == null) {
            mInstance = new Dispatcher();
        }
        return mInstance;
    }

    /**
     * 注册Store的回调接口
     *
     * @param store
     */
    public void register(final Store store) {
        stores.add(store);
    }

    /**
     * 解除Store的回调接口
     * PS:一定要记得在合适的时候调用该方法解除注册,否者会造成内存溢出!!!
     *
     * @param store
     */
    public void unregister(final Store store) {
        stores.remove(store);
    }

    public void dispatch(Action action) {
        post(action);
    }

    private void post(final Action action) {
        for (Store store : stores) {
            store.onAction(action);
        }
    }
}
