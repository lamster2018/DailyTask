package com.example.MultiThread;

/**
 * Project Name:DailyTask
 * Package Name:com.example.MultiThread
 * Created by lahm on 2017/4/4 下午4:04 .
 * <p>
 * https://github.com/lamster2018
 */

public class MultiThreadTest {
    public static void main(String[] a) {
        ticket t = new ticket(30);
        new Thread(t).start();
        new Thread(t).start();
        new Thread(t).start();
    }

    static class ticket implements Runnable {
        private volatile int num;//只能保证可见性，每个线程读的是最新的数据，不保证原子性
        //        private int num;
        Object lockObject = new Object();//同步代码块

        public ticket(int num) {
            this.num = num;
        }

        @Override
        public void run() {
            synchronized (lockObject) {
                while (num > 0) {
                    try {
                        Thread.sleep(500);
                        num--;//即使使用volatile关键字也没用，这个操作不是原子性的，所以任有可能会造成数据紊乱
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() +
                            "---still have---" + num);
                }
            }
        }
    }
}
