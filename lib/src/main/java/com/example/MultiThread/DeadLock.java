package com.example.MultiThread;

/**
 * Project Name:DailyTask
 * Package Name:com.example.MultiThread
 * Created by lahm on 2017/3/27 下午10:36 .
 * <p>
 * https://github.com/lamster2018
 */

public class DeadLock {
    private Object first = new Object();
    private Object second = new Object();

    public void firstSecond() throws InterruptedException {
        synchronized (first) {
            Thread.sleep(2000);
            synchronized (second) {
                System.out.println("先获取first锁然后获取second锁");
            }
        }
    }

    public void secondFirst() throws InterruptedException {
        synchronized (second) {
            Thread.sleep(2000);
            synchronized (first) {
                System.out.println("先获取second锁然后获取first锁");
            }
        }
    }
}
