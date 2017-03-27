package com.example.MultiThread;

/**
 * Project Name:DailyTask
 * Package Name:com.example.MultiThread
 * Created by lahm on 2017/3/27 下午10:43 .
 * <p>
 * https://github.com/lamster2018
 */

public class TestDeadLock {
    public static void main(String[] args) {
        DeadLock dl = new DeadLock();

        Thread1 t1 = new Thread1(dl);
        Thread2 t2 = new Thread2(dl);

        t1.start();
        t2.start();
        while (true) ;
    }

}
