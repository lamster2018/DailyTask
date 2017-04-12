package com.example.DesignMode.ConsumerModel;

/**
 * Project Name:DailyTask
 * Package Name:com.example.DesignMode.ConsumerModel
 * Created by lahm on 2017/4/12 下午5:46 .
 * <p>
 * https://github.com/lamster2018
 */

public class Consumer implements Runnable {
    // 每次消费的产品数量
    private int num;

    // 所在放置的仓库
    private Storage storage;

    // 构造函数，设置仓库
    public Consumer(Storage storage) {
        this.storage = storage;
    }

    // 线程run函数
    @Override
    public void run() {
        consume(num);
    }

    // 调用仓库Storage的生产函数
    public void consume(int num) {
        storage.consume(num);
    }

    // get/set方法
    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }
}
