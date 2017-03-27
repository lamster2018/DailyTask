package com.example.lahm.dailytask.Service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Project Name:DailyTask
 * Package Name:com.example.lahm.dailytask.Service
 * Created by lahm on 2017/3/3 下午3:53 .
 * https://github.com/lamster2018
 * <p>
 * 使用使用context.bindService()启动Service会经历：
 * context.bindService()->onCreate()->onBind()->Service running
 * onUnbind() -> onDestroy() ->Service stop
 * <p>
 * onBind将返回给客户端一个IBind接口实例，IBind允许客户端回调服务的方法，比如得到Service运行的状态或其他操作。
 * 这个时候把调用者（Context，例如Activity）会和Service绑定在一起，Context退出了，
 * Srevice就会调用onUnbind->onDestroy相应退出。
 * <p>
 * 所以调用bindService的生命周期为：onCreate --> onBind(只一次，不可多次绑定) --> onUnbind --> onDestory。
 * <p>
 * 在Service每一次的开启关闭过程中，只有onStart可被多次调用(通过多次startService调用)，
 * 其他onCreate，onBind，onUnbind，onDestory在一个生命周期中只能被调用一次。
 */

public class MyBindService extends Service {
    private String TAG = "MyBindService";

    public class MyBinder extends Binder {
        public MyBindService getMyService() {
            return MyBindService.this;
        }

        public void ccccc() {
            Log.i(TAG, "ccccc: ");
        }
    }

    private IBinder iBinder = new MyBinder();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "MyBindService-onBind: ");
        return iBinder;
    }

    public void abcdefg() {
        Log.i(TAG, "abcdefg: ");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG, "MyBindService-onUnbind: ");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "MyBindService-onDestroy: ");
        super.onDestroy();
    }
}
