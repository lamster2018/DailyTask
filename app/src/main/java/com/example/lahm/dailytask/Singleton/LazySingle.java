package com.example.lahm.dailytask.Singleton;

import android.util.Log;

/**
 * Project Name:DailyTask
 * Package Name:com.example.lahm.dailytask.Singleton
 * Created by lahm on 17/2/20 上午9:29 .
 * https://github.com/lamster2018
 */
public class LazySingle {
    /**
     * 因为对象的创建分三步，分配内存空间，初始化对象，将该对象内存空间地址赋给引用、
     * 其中二三步可能发生重排序，则DCL的第二次判断会出错，
     * lazySingle！=null（实际只是地址给了lazySingleton引用，而对象还未初始化）
     * 声明lazySingle这个值（本质是个指针）为volatile，避免发生重排序
     * 因为对volatile变量，也就是lazySingle进行读操作的时候，它后面的操作无法进行重排序
     * happen-before原则规定
     * http://cmsblogs.com/?p=2161
     */
    private volatile static LazySingle lazySingle;

    //thread unsafe mode
    public static LazySingle getLazySingle() {
        if (lazySingle == null)
            lazySingle = new LazySingle();
        return lazySingle;
    }

    //not recommend
    public static synchronized LazySingle getLazySingleSynchronized() {
        if (lazySingle == null)
            lazySingle = new LazySingle();
        return lazySingle;
    }

    //double check lock,many third library use this single pattern
    public static LazySingle getLazySingleDoubleLock() {
        if (lazySingle == null) {
            synchronized (LazySingle.class) {
                if (lazySingle == null) {
                    lazySingle = new LazySingle();
                }
            }
        }
        return lazySingle;
    }

    public void publicFunc() {
        System.out.println("publicFunc");
        int[] a = new int[]{22, 12, 5, 1, 50};
        a = selectSort(a);
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
    }

    private void privateFunc() {
        System.out.println("privateFunc");
    }

    private int[] selectSort(int[] arr) {
        int temp;
        for (int i = 0, len = arr.length; i < len; i++) {
            int minIndex = i;
            for (int j = i, len2 = arr.length; j < len2; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
            }
        }
        return arr;
    }
}
