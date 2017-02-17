package com.example.lahm.dailytask;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Project Name:DailyTask
 * Package Name:com.example.lahm.dailytask
 * Created by lahm on 17/2/16 下午4:43 .
 * Copyright (c) 2017, www.footballzone.com All Rights Reserved.
 */

public class LifeCircleActivity extends AppCompatActivity {
    private final String TAG = "lifeCircle";
    private MyBroadcastReceiver myBroadcastReceiver;

    class MyBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String msg = intent.getStringExtra("msg");
            Log.i(TAG, msg);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate: ");
        myBroadcastReceiver = new MyBroadcastReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.intent.action.MY_BROADCAST");
        registerReceiver(myBroadcastReceiver, filter);
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart: ");
        Intent intent = new Intent("android.intent.action.MY_BROADCAST");
        intent.putExtra("msg", "hello receiver.");
        sendBroadcast(intent);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        Log.i(TAG, "onPostCreate: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume: ");
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        Log.i(TAG, "onPostResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: ");
        //动态注册广播，一定要反注册，否则报错--has leaked IntentReceiver
        unregisterReceiver(myBroadcastReceiver);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart: ");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.i(TAG, "onBackPressed: ");
    }
}
