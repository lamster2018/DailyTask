package com.example.lahm.dailytask.RecyclerView;

/**
 * Project Name:DailyTask
 * Package Name:com.example.lahm.dailytask.RecyclerView
 * Created by lahm on 2017/6/24 上午8:24 .
 * <p>
 * Copyright (c) 2016—2017 https://www.lizhiweike.com all rights reserved.
 */

import org.parceler.Parcel;
import org.parceler.ParcelFactory;

@Parcel
public class BaseAccount {
    private String name;
    private int id;

    @ParcelFactory
    public static BaseAccount create(String name, int id) {
        return new BaseAccount(name, id);
    }

    public BaseAccount(String name, int id) {
        this.name = name;
        this.id = id;
    }
}
