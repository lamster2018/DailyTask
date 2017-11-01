package com.example.lahm.dailytask.daemon.jobScheduler;

import android.annotation.TargetApi;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;

/**
 * Project Name:android
 * Created by lahm on 2017/11/1 10:24 .
 * <p>
 */
public class JobSchedulerManager {
    private static final int JOB_ID = 1;
    private JobScheduler mJobScheduler;
    private Context mContext;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public JobSchedulerManager(Context context) {
        this.mContext = context;
        mJobScheduler = (JobScheduler) context.getSystemService(Context.JOB_SCHEDULER_SERVICE);
    }

    @TargetApi(21)
    public void startJobScheduler() {
        // 如果JobService已经启动或API<21，返回
        if (AliveJobService.isJobServiceAlive()
                || Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP)
            return;
        // 构建JobInfo对象，传递给JobSchedulerService
        JobInfo.Builder builder = new JobInfo.Builder(JOB_ID, new ComponentName(mContext, AliveJobService.class));
        // 设置每2秒执行一下任务，实际最短是15分钟才能执行一次
        builder.setPeriodic(3000);
        // 设置设备重启时，执行该任务
        builder.setPersisted(true);
        // 不需要插入充电器，执行该任务
        builder.setRequiresCharging(false);
        // 任何网络状态
        builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY);
        // 空闲，也就是doze
        builder.setRequiresDeviceIdle(false);
        JobInfo info = builder.build();
        //开始定时执行该系统任务
        mJobScheduler.schedule(info);
    }

    @TargetApi(21)
    public void stopJobScheduler() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) return;
        mJobScheduler.cancelAll();
    }
}
