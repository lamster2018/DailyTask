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

        final String k = "BIG";
//        k = k.toLowerCase();//编译错误
        //因为String是不可变类，声明的一个指向String对象的指针k，
        // k.toLow操作后会导致新的String产生，也就是k指向的对象的地址变化了
        // final编译冲突了呗

        final StringBuffer a = new StringBuffer("x");
        final StringBuffer b = new StringBuffer("y");
//        a=b;//编译错误
        final StringBuffer sb = new StringBuffer("z");
        sb.append("kkk");//sb指向的SB对象内容变化了
//        可见，final只对引用的“值”(也即它所指向的那个对象的内存地址) 有效，
//        它迫使引用只能指向初始指向的那个对象，改变它的指向会导致编译期错误。
//        至于它所指向的对象的变化，final是不负责的。这很类似 == 操作符：==操作符只负责引用的“值”相等，
//        至于这个地址所指向的对象内容是否相等，==操作符是不管的。
    }

}
