package com.example.lahm.dailytask.RecyclerView;

/**
 * Project Name:DailyTask
 * Package Name:com.example.lahm.dailytask.RecyclerView
 * Created by lahm on 2017/6/24 上午8:24 .
 * <p>
 * https://github.com/lamster2018
 */

import org.parceler.Parcel;
import org.parceler.ParcelFactory;

@Parcel
public class BaseAccount {
    public String name;
    public int id;

    @ParcelFactory
    public static BaseAccount create(String name, int id) {
        return new BaseAccount(name, id);
    }

    public BaseAccount(String name, int id) {
        this.name = name;
        this.id = id;
    }
}
