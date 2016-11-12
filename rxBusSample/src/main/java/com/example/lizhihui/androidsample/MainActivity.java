package com.example.lizhihui.androidsample;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.lizhihui.androidsample.rxBus.RxBus;
import com.example.lizhihui.androidsample.rxBus.event.TestEvent;
import com.trello.rxlifecycle.android.ActivityEvent;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import rx.functions.Action1;


/**
 * RxBus使用示例
 */
public class MainActivity extends RxAppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.activity_main).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Main2Activity.class));
            }
        });

        RxBus.getInstance()
                .toObserverable(TestEvent.class)
                /**
                 * this.<TestEvent>bindUntilEvent(ActivityEvent.DESTROY):【手动】手动指定序列结束的时间
                 * this.<TestEvent>bindToLifecycle():【自动】让RxLifecycle自动在适当的时间结束序列
                 *
                 * PS:bindXXX()前要加泛型,泛型类型需要和observer的泛型类型保持一致
                 */
                .compose(this.<TestEvent>bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(new Action1<TestEvent>() {
                    @Override
                    public void call(TestEvent studentEvent) {
                        Toast.makeText(MainActivity.this, "id:"+ studentEvent.getId()+"  name:"+ studentEvent.getName(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
