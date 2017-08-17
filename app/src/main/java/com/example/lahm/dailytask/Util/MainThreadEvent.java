package com.example.lahm.dailytask.Util;

/**
 * Project Name:DailyTask
 * Package Name:com.example.lahm.dailytask.Util
 * Created by Administrator on 2017/8/17 0017 17:30 .
 * <p>
 * Copyright (c) 2016—2017 https://www.lizhiweike.com all rights reserved.
 */
public class MainThreadEvent<T> {
    private int type;
    private T content;

    public MainThreadEvent(int type, T content) {
        this.type = type;
        this.content = content;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }
}
