package com.example.DesignMode.ConsumerModel;

/**
 * Project Name:DailyTask
 * Package Name:com.example.DesignMode.ConsumerModel
 * Created by lahm on 2017/4/12 下午5:48 .
 * <p>
 * https://github.com/lamster2018
 * <p>
 * http://blog.csdn.net/MONKEY_D_MENG/article/details/6251879
 * 和blockingQueue的方法内涵是一样的，前者封装了一下
 * http://www.cnblogs.com/bendantuohai/p/4738751.html
 */

public class ProducerConsumerPattern {
    public static void main(String[] args) {
        // 仓库对象
        Storage storage = new Storage();

        // 生产者对象
        Producer p1 = new Producer(storage);
        Producer p2 = new Producer(storage);
        Producer p3 = new Producer(storage);
        Producer p4 = new Producer(storage);
        Producer p5 = new Producer(storage);
        Producer p6 = new Producer(storage);
        Producer p7 = new Producer(storage);

        // 消费者对象
        Consumer c1 = new Consumer(storage);
        Consumer c2 = new Consumer(storage);
        Consumer c3 = new Consumer(storage);

        // 设置生产者产品生产数量
        p1.setNum(10);
        p2.setNum(10);
        p3.setNum(10);
        p4.setNum(10);
        p5.setNum(10);
        p6.setNum(10);
        p7.setNum(60);

        // 设置消费者产品消费数量
        c1.setNum(50);
        c2.setNum(20);
        c3.setNum(30);

        // 线程开始执行
        new Thread(c1).start();
        new Thread(c2).start();
        new Thread(c3).start();
        new Thread(p1).start();
        new Thread(p2).start();
        new Thread(p3).start();
        new Thread(p4).start();
        new Thread(p5).start();
        new Thread(p6).start();
        new Thread(p7).start();
    }
}
