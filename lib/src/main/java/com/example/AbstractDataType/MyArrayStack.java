package com.example.AbstractDataType;

/**
 * Project Name:DailyTask
 * Package Name:com.example
 * Created by lahm on 2017/2/24 上午10:13 .
 * Copyright (c) 2017, www.footballzone.com All Rights Reserved.
 * <p>
 * http://blog.csdn.net/fengyifei11228/article/details/5625961
 */

public class MyArrayStack {
    private Object[] objects;
    private int top;//栈顶位置
    private int size;

    public MyArrayStack(int size) {
        this.size = size;
        objects = new Object[size];
        top = -1;
    }

    public int getTop() {
        return top;
    }

    public int getSize() {
        return size;
    }

    public int getElementCount() {
        return top + 1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top + 1 == size;
    }

    /**
     * 入栈
     *
     * @param data
     * @return
     */
    public boolean push(Object data) {
        if (isFull()) {
            System.out.println("full stack !");
            return false;
        } else {
            objects[++top] = data;
            return true;
        }
    }

    /**
     * 出栈
     *
     * @return
     * @throws Exception
     */
    public Object pop() throws Exception {
        if (isEmpty()) throw new Exception("empty stack !");
//        if (isEmpty()) return null;
        return objects[top--];
    }

    /**
     * 得到栈顶元素
     *
     * @return
     */
    public Object peek() {
        if (isEmpty()) return null;
        else return objects[getElementCount()];
    }

    public void print() {
        System.out.println("isEmpty--" + isEmpty());
        System.out.println("isFull--" + isFull());
        System.out.println("getSize --" + getSize());
        System.out.println("getElementCount--" + getElementCount());
        System.out.println("\n\n");
    }
}
