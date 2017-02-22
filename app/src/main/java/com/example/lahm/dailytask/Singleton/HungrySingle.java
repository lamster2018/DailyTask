package com.example.lahm.dailytask.Singleton;

import android.util.Log;

/**
 * Project Name:DailyTask
 * Package Name:com.example.lahm.dailytask.Singleton
 * Created by lahm on 17/2/20 上午9:26 .
 * Copyright (c) 2017, www.footballzone.com All Rights Reserved.
 */
public class HungrySingle {
    private static HungrySingle hungrySingle = new HungrySingle();

    public static HungrySingle getHungrySingle() {
        return hungrySingle;
    }

    public void publicFunc() {
        System.out.println("publicFunc");
        int[] result = getArr();
        result = bubble(result);
        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }
        privateFunc();
    }

    private void privateFunc() {
        System.out.println("privateFunc");
    }

    //    private int[] arr;// 这样如果不赋值，会null
    private int[] arr = new int[]{};

    public void setArray(int[] array) {
        arr = array;
    }

    private int[] getArr() {
        return arr;
    }

    public static int[] bubble(int[] arr) {
        int temp;
        for (int i = 0, len1 = arr.length; i < len1; i++) {
            for (int j = i, len2 = arr.length; j < len2; j++) {
                if (arr[i] > arr[j]) {//升序
                    temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        return arr;
    }
}
