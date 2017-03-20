package com.example;

import com.example.AbstractDataType.MyArrayStack;
import com.example.AbstractDataType.MyListStack;

import java.util.ArrayList;
import java.util.LinkedList;

public class JavaLib {
    public static void main(String[] args) {

//        testList();
        try {
            testArrayStack();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            testListStack();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void testListStack() throws Exception {
        MyListStack<String> emptyListStack = new MyListStack<>();
        emptyListStack.print();

        MyListStack<String> myListStack = new MyListStack<>(5);
        myListStack.print();
        myListStack.push("1");
        myListStack.push("2");
        myListStack.push("3");
        myListStack.print();
        myListStack.pop();
        myListStack.push("22");
        myListStack.push("10");
        myListStack.push("66");
        myListStack.print();
        myListStack.push("1234");
        while (myListStack.getElementCount() > 0) {
            System.out.println(myListStack.pop());
        }

    }

    private static void testArrayStack() throws Exception {
        MyArrayStack myArrayStack = new MyArrayStack(5);

        myArrayStack.push("1");
        myArrayStack.push("3");
        myArrayStack.push("2");
        myArrayStack.print();
        myArrayStack.pop();
        myArrayStack.print();
        myArrayStack.push("21");
        myArrayStack.push("0");
        myArrayStack.push("10");
        myArrayStack.print();
        myArrayStack.push("12222");

        while (myArrayStack.getTop() >= 0) {
            System.out.println(myArrayStack.pop());
        }
    }

    private static void testList() {
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
