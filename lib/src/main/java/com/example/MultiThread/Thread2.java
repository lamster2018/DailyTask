package com.example.MultiThread;

/**
 * Project Name:DailyTask
 * Package Name:com.example.MultiThread
 * Created by lahm on 2017/3/27 下午10:49 .
 * <p>
 * https://github.com/lamster2018
 */

public class Thread2 extends Thread {
    private DeadLock Thread2;

    public Thread2(DeadLock Thread2) {
        this.Thread2 = Thread2;
    }

    @Override
    public void run() {
        super.run();
        try {
            Thread2.secondFirst();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
