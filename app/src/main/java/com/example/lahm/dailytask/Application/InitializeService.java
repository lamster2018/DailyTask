package com.example.lahm.dailytask.Application;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Project Name:DailyTask
 * Package Name:com.example.lahm.dailytask.Application
 * Created by lahm on 2017/4/10 上午9:45 .
 * <p>
 * https://github.com/lamster2018
 * <p>
 * 使用http://blog.csdn.net/hudashi/article/details/7986130
 * 和service的区别http://blog.csdn.net/matrix_xu/article/details/7974393
 * 和service的区别https://www.pocketdigi.com/20130827/1168.html
 */

public class InitializeService extends IntentService {
    public static final String ACTION_INIT_WHEN_APP_CREATE = "com.example.lahm.dailytask.Application.INIT";

    public InitializeService() {
        super("InitializeService");
    }

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     * 父类是个抽象类，必须通过继承实现，不能直接new；
     * 实现intentService必须
     * 1、写构造函数，这个构造函数就还必须按代码提示那样写
     * 2、实现虚函数onHandleIntent，并在里面根据Intent的不同进行不同的事务处理就可以了。
     *
     * @param name Used to name the worker thread, important only for debugging.
     */

    public InitializeService(String name) {
        super(name);
    }

    //实际上就是传intent,通过不同的action判断intent，供后续区分如何操作
    public static void start(Context context, String action) {
        Intent intent = new Intent(context, InitializeService.class);
        intent.setAction(action);
        context.startService(intent);
    }

    String TAG = "service";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate: ");
    }

    @Override
    public void onStart(@Nullable Intent intent, int startId) {
        super.onStart(intent, startId);
        Log.i(TAG, "onStart: ");
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand: ");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: ");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return super.onBind(intent);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            switch (action) {
                case ACTION_INIT_WHEN_APP_CREATE:
                    performInit();
                    break;
                default:
                    break;
            }
        }
    }

    private void performInit() {
//        CrashUtil.getInstance().init(getApplicationContext());
        // init crash helper
//        CrashHelper.init(this.getApplicationContext());

        // init Push
//        PushPlatform.init(this.getApplicationContext());

        // init Feedback
//        FeedbackPlatform.init(this.getApplication());

        // init Share
//        SharePlatform.init(this.getApplicationContext());
    }
}
