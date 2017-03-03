package com.example.lahm.dailytask;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Project Name:DailyTask
 * Package Name:com.example.lahm.dailytask
 * Created by lahm on 2017/3/3 下午3:55 .
 * Copyright (c) 2017, www.footballzone.com All Rights Reserved.
 */

public class MyBroadcastReceiver extends BroadcastReceiver {
    private String TAG = "MyBroadcastReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        String msg = intent.getStringExtra("msg");
        Log.i(TAG, msg);
    }
}
