package com.example.lahm.dailytask.Singleton;

import android.util.Log;

/**
 * Project Name:DailyTask
 * Package Name:com.example.lahm.dailytask.Singleton
 * Created by lahm on 17/2/20 上午9:29 .
 * Copyright (c) 2017, www.footballzone.com All Rights Reserved.
 */
public class LazySingle {
    private static LazySingle lazySingle;

    public static LazySingle getLazySingle() {
        if (lazySingle == null)
            lazySingle = new LazySingle();
        return lazySingle;
    }

    public static synchronized LazySingle getLazySingleSynchronized() {
        if (lazySingle == null)
            lazySingle = new LazySingle();
        return lazySingle;
    }

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
