package com.example.lahm.dailytask.Application;

import android.app.Application;

/**
 * Project Name:DailyTask
 * Package Name:com.example.lahm.dailytask
 * Created by lahm on 2017/4/10 上午9:40 .
 * <p>
 * https://github.com/lamster2018
 * how to speed launching our app?
 * many third lib require initialize on Application's onCreate or construct code block
 * <p>
 * http://www.jianshu.com/p/4f10c9a10ac9
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        InitializeService.start(this, InitializeService.ACTION_INIT_WHEN_APP_CREATE);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        //use onTrimMemory
    }
}
