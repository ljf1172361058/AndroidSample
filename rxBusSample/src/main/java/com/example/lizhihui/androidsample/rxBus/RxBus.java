package com.example.lizhihui.androidsample.rxBus;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * 自定义RxBus
 * 替代BroadcastReceiver或Handler或接口或EventBus实现的组件通信
 *
 * Created by LiZhiHui on 2016/11/11 16:10.
 */

public class RxBus {

    private static volatile RxBus mInstance;

    private final Subject<Object, Object> mRxBus;

    private RxBus()
    {
        mRxBus = new SerializedSubject<>(PublishSubject.create());
    }

    /**
     * 单例模式RxBus
     *
     * @return RxBus
     */
    public static RxBus getInstance()
    {
        RxBus rxBus = mInstance;
        if (mInstance == null)
        {
            synchronized (RxBus.class)
            {
                rxBus = mInstance;
                if (mInstance == null)
                {
                    rxBus = new RxBus();
                    mInstance = rxBus;
                }
            }
        }
        return rxBus;
    }

    /**
     * 发送事件
     */
    public void send(Object object) {
        if (hasObservers()) {
            mRxBus.onNext(object);
        }
    }

    /**
     * 接收消息(根据传递的类型(eventType)返回特定类型(eventType)的被观察者)
     *
     * @return <T> Observable<T>
     */
    public <T> Observable<T> toObserverable(Class<T> eventType)
    {
        // ofType操作符只发射指定类型的数据，其内部就是filter+cast
        return mRxBus.ofType(eventType);
    }

    /**
     * 判断是否有订阅者
     */
    private boolean hasObservers() {
        return mRxBus.hasObservers();
    }
}
