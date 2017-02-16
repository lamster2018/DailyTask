package com.example.lahm.dailytask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TextView;

public class ScreenActivity extends AppCompatActivity {
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen);
        result = (TextView) findViewById(R.id.screen_result);
        result.setText(getScreenParams().toString());
    }

    private StringBuffer getScreenParams() {
        StringBuffer sb = new StringBuffer();
        DisplayMetrics dm = getResources().getDisplayMetrics();
        int screenWidth = dm.widthPixels;
        sb.append("宽--").append(String.valueOf(screenWidth)).append("\n");
        int screenHeight = dm.heightPixels;
        sb.append("高--").append(String.valueOf(screenHeight)).append("\n");
        int densityDpi = dm.densityDpi;
        sb.append("dpi").append(String.valueOf(densityDpi)).append("\n");
        float density = dm.density;
        sb.append("density--").append(String.valueOf(density)).append("\n");
        float scaledDensity = dm.scaledDensity;
        sb.append("scaledDensity").append(String.valueOf(scaledDensity)).append("\n");
        return sb;
    }

    @Override
    protected void onResume() {
        super.onResume();
        Ticket t = new Ticket();
        TicketSynchronized ticketSynchronized = new TicketSynchronized();

//        new Thread(t).start();
//        new Thread(t).start();
//        new Thread(t).start();

//        new Thread(ticketSynchronized).start();
//        new Thread(ticketSynchronized).start();
//        new Thread(ticketSynchronized).start();

        Cus cus = new Cus();//银行问题
        Thread t1 = new Thread(cus);
        Thread t2 = new Thread(cus);
        t1.start();
        t2.start();

//        CusSync cus = new CusSync();
//        Thread t1 = new Thread(cus);
//        Thread t2 = new Thread(cus);
//        t1.start();
//        t2.start();
    }

    //存在线程同步问题，会出现0，-1，-2的情况
    class Ticket implements Runnable {
        private int ticket = 20;

        public void run() {
            while (true) {
                if (ticket > 0) {
                    try {
                        Thread.sleep(500);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "..sale:" + ticket--);
                }
            }
        }
    }

    //解决线程同步问题
    class TicketSynchronized implements Runnable {
        private Bank bank = new Bank();

        private int tick = 20;
        Object obj = new Object();

        public void run() {
            while (true) {
                synchronized (obj) {
                    if (tick > 0) {
                        try {
                            Thread.sleep(500);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        bank.add(1);
                        System.out.println(Thread.currentThread().getName() + "....sale : " + tick--);
                    }
                }
            }
        }
    }
}

class Bank {
    private int sum;
    Object object = new Object();

    public void add(int n) {
        synchronized (object) {
            sum = sum + n;
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " 银行的钱-->" + sum);
        }
    }
}

class Cus implements Runnable {
    private Bank bank = new Bank();

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            bank.add(100);
        }
    }
}

class Bank2 {
    private int sum;

    public synchronized void add(int n) {
        sum = sum + n;
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " 银行的钱-->" + sum);
    }
}

class CusSync implements Runnable {
    private Bank2 bank = new Bank2();

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            bank.add(100);
        }

    }
}