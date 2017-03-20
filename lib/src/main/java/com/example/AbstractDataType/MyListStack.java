package com.example.AbstractDataType;

/**
 * Project Name:DailyTask
 * Package Name:com.example
 * Created by lahm on 2017/2/24 上午10:43 .
 * Copyright (c) 2017, www.footballzone.com All Rights Reserved.
 */

public class MyListStack<T> {
    /**
     * 节点
     *
     * @param <T>
     */
    private static class Node<T> {
        public T data;
        public Node<T> bottom;

        public Node(T data, Node<T> bottom) {
            this.data = data;
            this.bottom = bottom;
        }
    }

    private Node<T> top;
    private int stackSize;
    private int elementCount;

    /**
     * construct a new empty stack
     */
    public MyListStack() {
        top = null;
        elementCount = 0;
        stackSize = 0;
    }

    public MyListStack(int size) {
        top = null;
        elementCount = 0;
        stackSize = size;
    }

    public void setTop(Node<T> top) {
        this.top = top;
    }

    public void setStackSize(int stackSize) {
        this.stackSize = stackSize;
    }

    public int getStackSize() {
        return stackSize;
    }

    public int getElementCount() {
        return elementCount;
    }

    public boolean isEmpty() {
        if (elementCount == 0) return true;
        else return false;
    }

    public boolean isFull() {
        if (elementCount == stackSize) return true;
        else return false;
    }

    public boolean push(T data) throws Exception {
        if (isFull()) throw new Exception("full stack !");
        else {
            top = new Node<>(data, top);
            elementCount++;
            return true;
        }
    }

    public T pop() throws Exception {
        if (isEmpty()) throw new Exception("empty stack !");
        else {
            Node<T> node = top;
            top = top.bottom;
            elementCount--;
            return node.data;
        }
    }

    public T peek() {
        if (isEmpty()) return null;
        else return top.data;
    }

    public T getNext() {
        if (isEmpty()) return null;
        else {
            if (top.bottom == null) return null;
            else return top.bottom.data;
        }
    }

    public void print() {
        System.out.println("isEmpty-- " + isEmpty());
        System.out.println("isFull-- " + isFull());
        System.out.println("getStackSize-- " + getStackSize());
        System.out.println("getElementCount-- " + getElementCount());
        System.out.println("\n\n");
    }
}
