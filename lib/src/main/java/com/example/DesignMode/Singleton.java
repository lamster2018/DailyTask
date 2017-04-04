package com.example.DesignMode;

/**
 * Project Name:DailyTask
 * Package Name:com.example.DesignMode
 * Created by lahm on 2017/4/4 下午3:24 .
 * <p>
 * https://github.com/lamster2018
 * 这个写烂了，最低要求是非常熟练的写出DCL，
 * http://mp.weixin.qq.com/s/pixuEDQ_OZ0RFjciTThKlQ
 * 我个人倾向用enum写法,根本不用考虑线程安全问题,至于为什么枚举类是线程安全的
 * http://www.hollischuang.com/archives/197
 */

public class Singleton {
    private volatile Singleton singleton;

    public Singleton getSingleInstance() {
        if (singleton == null) {
            synchronized (Singleton.class) {
                if (singleton == null) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }
}
