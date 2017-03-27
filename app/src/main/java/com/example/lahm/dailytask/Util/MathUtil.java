package com.example.lahm.dailytask.Util;

/**
 * Project Name:DailyTask
 * Package Name:com.example.lahm.dailytask.Util
 * Created by lahm on 2017/2/22 下午2:50 .
 * https://github.com/lamster2018
 */

public class MathUtil {
    /**
     * 给数据扩大容量
     *
     * @param arr
     * @return
     */
    public static int[] exbandArrayLength(int[] arr) {
        int[] newArr = new int[arr.length * 2];
        for (int i = 0, len = arr.length; i < len; i++) {
            newArr[i] = arr[i];
        }
        arr = newArr;
        return arr;
    }
}
