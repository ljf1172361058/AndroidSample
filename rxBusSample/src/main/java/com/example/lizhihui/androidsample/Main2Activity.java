package com.example.lizhihui.androidsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.lizhihui.androidsample.rxBus.RxBus;
import com.example.lizhihui.androidsample.rxBus.event.TestEvent;

public class Main2Activity extends AppCompatActivity {
    private int i = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        findViewById(R.id.activity_main2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RxBus.getInstance().send(new TestEvent("" + i, "小明"));
                i++;
            }
        });
    }
}
