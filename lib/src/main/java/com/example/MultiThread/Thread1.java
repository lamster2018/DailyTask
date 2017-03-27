package com.example.MultiThread;

/**
 * Project Name:DailyTask
 * Package Name:com.example.MultiThread
 * Created by lahm on 2017/3/27 下午10:48 .
 * <p>
 * https://github.com/lamster2018
 */

public class Thread1 extends Thread {
    private DeadLock deadLock1;

    public Thread1(DeadLock deadLock1) {
        this.deadLock1 = deadLock1;
    }

    @Override
    public void run() {
        super.run();
        try {
            deadLock1.firstSecond();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
