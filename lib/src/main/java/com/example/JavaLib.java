package com.example;

import java.util.ArrayList;
import java.util.LinkedList;

public class JavaLib {
    public static void main(String[] args) {

        long start = System.currentTimeMillis();
        makeArrayList(100000);
        System.out.println(System.currentTimeMillis() - start);

        long again = System.currentTimeMillis();
        makeLinkedList(100000);
        System.out.println(System.currentTimeMillis() - again);

    }

    private static int[] expandArrayLength(int[] arr) {
        int[] newArr = new int[arr.length * 2];
        for (int i : arr) {
            newArr[i] = arr[i];
        }
        arr = newArr;
        return arr;
    }

    /**
     * arrayList后端添加复杂度 On，前端添加On2
     *
     * @param n
     */
    private static void makeArrayList(int n) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
//            arrayList.add(i);
            arrayList.add(0, i);
        }
    }

    /**
     * linkedList后端添加复杂度 On，前端添加On
     *
     * @param n
     */
    private static void makeLinkedList(int n) {
        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i < n; i++) {
//            linkedList.add(i);
            linkedList.add(0, i);
        }
    }
}
