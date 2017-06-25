package com.example.lahm.dailytask.RecyclerView;

/**
 * Project Name:DailyTask
 * Package Name:com.example.lahm.dailytask.RecyclerView
 * Created by lahm on 2017/6/24 上午8:21 .
 * <p>
 * Copyright (c) 2016—2017 https://www.lizhiweike.com all rights reserved.
 */

import org.parceler.Parcel;
import org.parceler.ParcelFactory;


@Parcel
public class DataModel {
    public BaseAccount account;
    public String message;

    @ParcelFactory
    public static DataModel create(BaseAccount account, String message) {
        return new DataModel(account, message);
    }

    public DataModel(BaseAccount account, String message) {
        this.account = account;
        this.message = message;
    }
}
