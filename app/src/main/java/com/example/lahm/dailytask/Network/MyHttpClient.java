package com.example.lahm.dailytask.Network;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Project Name:DailyTask
 * Package Name:com.example.lahm.dailytask.Network
 * Created by lahm on 2017/4/17 上午9:37 .
 * <p>
 * https://github.com/lamster2018
 */

public class MyHttpClient {
    public static final OkHttpClient okHttpClient = new OkHttpClient.Builder()
//            .addNetworkInterceptor()
//            .addInterceptor()
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build();
//    public static MyHttpClient getInstance() {
//        return ;
//    }
}
