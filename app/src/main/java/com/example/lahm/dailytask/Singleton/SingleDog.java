package com.example.lahm.dailytask.Singleton;

/**
 * Project Name:DailyTask
 * Package Name:com.example.lahm.dailytask.Singleton
 * Created by lahm on 17/2/19 下午4:21 .
 * https://github.com/lamster2018
 */

public enum SingleDog {
    INSTANCE;

    public void publicFunc() {
        System.out.println("publicFunc");
        int[] result = getArr();
        result = bubbleSort(result);
        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }
        privateFunc();
    }

    private void privateFunc() {
        System.out.println("privateFunc");
    }

    private int[] arr = new int[]{};

    public void setArray(int[] array) {
        arr = array;
    }

    private int[] getArr() {
        return arr;
    }

    private int[] bubbleSort(int[] arr) {
        int temp;
        boolean flag;
        for (int i = 0, len = arr.length; i < len; i++) {
            flag = false;
            for (int j = i, len2 = arr.length; j < len2; j++) {
                if (arr[i] > arr[j]) {
                    temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                    flag = true;
                }
            }
            if (!flag) break;
        }
        return arr;
    }
}