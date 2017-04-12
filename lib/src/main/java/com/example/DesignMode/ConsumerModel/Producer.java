package com.example.DesignMode.ConsumerModel;

/**
 * Project Name:DailyTask
 * Package Name:com.example.DesignMode.ConsumerModel
 * Created by lahm on 2017/4/12 下午5:47 .
 * <p>
 * https://github.com/lamster2018
 */

public class Producer implements Runnable {

    // 每次生产的产品数量
    private int num;

    // 所在放置的仓库
    private Storage storage;

    // 构造函数，设置仓库
    public Producer(Storage storage) {
        this.storage = storage;
    }

    // 线程run函数
    @Override
    public void run() {
        produce(num);
    }

    // 调用仓库Storage的生产函数
    public void produce(int num) {
        storage.produce(num);
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
