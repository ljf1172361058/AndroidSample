package com.example.lizhihui.androidfluxsample.rxBus.event;

/**
 * Created by lizhihhui on 2016/11/11 17:11.
 */

public class TestEvent {
    private String id;
    private String name;

    public TestEvent(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
