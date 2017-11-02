package com.example.lahm.dailytask.daemon.jobScheduler;

import android.annotation.TargetApi;
import android.app.Service;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;

import com.example.lahm.dailytask.MainActivity;
import com.example.lahm.dailytask.daemon.DaemonUtil;


/**
 * Project Name:android
 * Created by lahm on 2017/11/1 10:19 .
 * <p>
 * 任务调度，不是保活，而是复活策略
 */
@TargetApi(21)
public class AliveJobService extends JobService {
    private static final int MESSAGE_ID_TASK = 0x01;
    // 告知编译器，这个变量不能被优化
    private volatile static Service mKeepAliveService = null;

    public static boolean isJobServiceAlive() {
        return mKeepAliveService != null;
    }

    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            // 具体任务逻辑
            if (DaemonUtil.isAPPALive(getApplicationContext(), getPackageName())) {
                //do nothing
            } else {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
            // 通知系统任务执行结束
            jobFinished((JobParameters) msg.obj, false);
            return true;
        }
    });

    @Override
    public boolean onStartJob(JobParameters params) {
        mKeepAliveService = this;
        // 返回false，系统假设这个方法返回时任务已经执行完毕；
        // 返回true，系统假定这个任务正要被执行
        Message msg = Message.obtain(mHandler, MESSAGE_ID_TASK, params);
        mHandler.sendMessage(msg);
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        mHandler.removeMessages(MESSAGE_ID_TASK);
        return false;
    }
}