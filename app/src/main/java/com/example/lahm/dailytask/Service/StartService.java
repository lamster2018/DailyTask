package com.example.lahm.dailytask.Service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Project Name:DailyTask
 * Package Name:com.example.lahm.dailytask.Service
 * Created by lahm on 2017/3/3 下午3:51 .
 * https://github.com/lamster2018
 * <p>
 * 使用context.startService() 启动Service是会会经历:
 * context.startService()  ->onCreate()- >onStart()->Service running
 * context.stopService() | ->onDestroy() ->Service stop
 * <p>
 * 如果Service还没有运行，则android先调用onCreate()然后调用onStart()；如果Service已经运行，则只调用onStart()，
 * 所以一个Service的onStart方法可能会重复调用多次。
 * <p>
 * stopService的时候直接onDestroy，如果是调用者自己直接退出而没有调用stopService的话，Service会一直在后台运行。
 * 该Service的调用者再启动起来后可以通过stopService关闭Service。
 * <p>
 * 所以调用startService的生命周期为：onCreate --> onStart(可多次调用) --> onDestroy
 * <p>
 * onStart被startCommand取代，5.0以上startService必须显示启动
 * <p>
 * Intent service_action_intent = new Intent();
 * service_action_intent.setAction("com.abcd.MyService");--清单中注册的action名
 * service_action_intent.setPackage("com.example.lahm.dailytask");--应用包名
 * startService(service_action_intent);
 * <p>
 * Intent service_action_intent = new Intent("com.abcd.MyService");
 * 隐式启动会报
 * Service Intent must be explicit异常
 */

public class StartService extends Service {
    private String TAG = "StartService";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "MyStartService-onBind: ");
        return null;
    }//have to implement but do not have to overwrite it

    @Override
    public void onCreate() {
        Log.i(TAG, "MyStartService-onCreate: ");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand-onStartCommand: ");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "MyStartService-onDestroy: ");
        super.onDestroy();
    }
}
