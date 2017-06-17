package com.example.lahm.dailytask.Util;

/**
 * Project Name:DailyTask
 * Package Name:com.example.lahm.dailytask.Util
 * Created by lahm on 2017/6/17 下午5:08 .
 * <p>
 * * 简单的说一下Monitor的意思,波纹最高的地方，就是代表当前内存使用情况已经达到触发GC垃圾回收。
 * GC工作时,当前的所有线程,包括UiThread,都将被短暂的暂停,等到GC工作完后才恢复正常。
 * 偶尔几次还是不能够造成界面卡顿,但是如果频繁的触发GC工作,必然会对界面造成卡顿
 * 内存抖动的原因 ：频繁触发垃圾回收
 * 优化也简单：避免频繁的触发GC
 */

public class MemUtil {
    private final static int MB = 1024 * 1024;

    public static void causeGC() {//原因在for循环里创建了大量对象,申请了许多内存,目标是定位到这个方法
        for (int i = 0; i < 50; i++) {
            byte[] b1, b2, b3, b4;
            b1 = new byte[MB];
            b2 = new byte[MB];
            b3 = new byte[MB];
            b4 = new byte[MB];
        }
    }
}
