package com.example;

/**
 * Project Name:DailyTask
 * Package Name:com.example
 * Created by lahm on 2017/2/24 下午7:59 .
 * Copyright (c) 2017, www.footballzone.com All Rights Reserved.
 */

public class MyArrayQueue {
    private Object[] queue;
    private int length;
    private int front;//实际上是一个游标/指针
    private int rear;
    private int itemNum;

    public MyArrayQueue(int length) {
        if (length >= 0) {
            this.length = length;
            queue = new Object[length];
            itemNum = front = rear = 0;
        } else {
            throw new RuntimeException("初始化大小不对");
        }
    }

    public boolean isEmpty() {
        return itemNum == 0;
    }

    public boolean isFull() {
        return itemNum == length;
    }

    public int getLength() {
        return length;
    }

    public int getItemNum() {
        return itemNum;
    }

    public boolean insert(Object data) throws Exception {
        if (isFull()) throw new Exception("full queue");
        else {
            queue[rear++] = data;
            return true;
        }
    }

    public boolean remove() throws Exception {
        if (isEmpty()) throw new Exception("empty queue");
        else {
            queue[front++] = null;
            return true;
        }
    }

}
