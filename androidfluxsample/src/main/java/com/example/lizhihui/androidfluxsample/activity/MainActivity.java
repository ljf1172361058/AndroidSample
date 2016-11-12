package com.example.lizhihui.androidfluxsample.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.lizhihui.androidfluxsample.R;
import com.example.lizhihui.androidfluxsample.actions.ActionCreator;
import com.example.lizhihui.androidfluxsample.dispatcher.Dispatcher;
import com.example.lizhihui.androidfluxsample.rxBus.RxBus;
import com.example.lizhihui.androidfluxsample.stores.MessageStore;
import com.example.lizhihui.androidfluxsample.stores.Store;
import com.trello.rxlifecycle.android.ActivityEvent;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import rx.functions.Action1;

public class MainActivity extends RxAppCompatActivity {
    private Dispatcher mDispatcher;
    private ActionCreator mActionCreator;
    private MessageStore mStore;

    private int i = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initDependencies();
        // 订阅事件
        RxBus.getInstance()
                .toObserverable(Store.StoreChangeEvent.class)
                /**
                 * this.<TestEvent>bindUntilEvent(ActivityEvent.DESTROY):【手动】手动指定序列结束的时间
                 * this.<TestEvent>bindToLifecycle():【自动】让RxLifecycle自动在适当的时间结束序列
                 *
                 * PS:bindXXX()前要加泛型,泛型类型需要和observer的泛型类型保持一致
                 */
                .compose(this.<Store.StoreChangeEvent>bindUntilEvent(ActivityEvent.PAUSE))
                .subscribe(new Action1<Store.StoreChangeEvent>() {
                    @Override
                    public void call(Store.StoreChangeEvent storeChangeEvent) {
                        Toast.makeText(MainActivity.this, mStore.getMessage() + "__" + i, Toast.LENGTH_SHORT).show();
                        i++;
                    }
                });

        findViewById(R.id.activity_main).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("test", "开始");
                mActionCreator.sendMessage("测试一下");
            }
        });
    }

    private void initDependencies() {
        mDispatcher = Dispatcher.getInstance();
        mActionCreator = ActionCreator.getInstance(mDispatcher);
        mStore = new MessageStore();
        mDispatcher.register(mStore);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 一定要记得及时调用该方法取消注册哦
        mDispatcher.unregister(mStore);
    }

}
