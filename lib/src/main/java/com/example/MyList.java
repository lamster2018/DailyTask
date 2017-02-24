package com.example;

/**
 * Project Name:DailyTask
 * Package Name:com.example
 * Created by lahm on 2017/2/23 上午11:34 .
 * Copyright (c) 2017, www.footballzone.com All Rights Reserved.
 */

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 实现双向链表，
 * 增删查功能
 */
public class MyList<T> implements Iterable<T> {

    //节点类，链表的静态内部类
    private static class Node<T> {
        public T data;
        public Node<T> prev;
        public Node<T> next;

        public Node(T Data, Node<T> Prev, Node<T> Next) {
            data = Data;
            prev = Prev;
            next = Next;
        }
    }

    public int listSize;
    public int modCount = 0;//代表自构造以后，所有对链表的操作改变的次数，用于迭代器差错
    private Node<T> beginMarker;
    private Node<T> endMarker;

    public MyList() {
        clear();
    }

    //清空链表，头尾节点互相连上
    public void clear() {
        beginMarker = new Node<>(null, null, null);
        endMarker = new Node<>(null, beginMarker, null);
        beginMarker.next = endMarker;
        listSize = 0;
        modCount++;
    }

    public int getListSize() {
        return listSize;
    }

    public boolean isListEmpty() {
        return listSize == 0;
    }

    //只有node的数据
    public T get(int index) {
        return getNode(index).data;
    }

    /**
     * 书上的，为什么要返回oldData?查看arrList和LinkedList的set都有返回值
     * 就是为了让你看看你改了什么值
     * <p>
     * http://ask.csdn.net/questions/162743
     */
    public T set(int index, T newData) {
        Node<T> oldNode = getNode(index);
        T oldData = oldNode.data;
        oldNode.data = newData;
        return oldData;
    }

    public boolean add(T x) {
        add(getListSize(), x);
        return true;
    }

    public void add(int index, T x) {
        addBefore(getNode(index), x);
    }

    /**
     * 双向链表添加
     * 原表结构  a 《-> indexNode《-> b
     * 新添加进来 newNode,创建构造的时候是可以知道
     * a(也就是indexNode.prev）《- newNode-> indexNode
     * 接下来就要   把 a(此时结构变了，它的指针是newNode.prev)-> newNode
     * 和newNode《- indexNode加上
     * <p>
     * 混淆点在a的指代上，一开始a是indexMode的前驱节点，但是newNode创建后，
     * indexNode的前驱是newNode，newNode后驱是indexNode
     *
     * @param indexNode
     * @param x
     */
    private void addBefore(Node<T> indexNode, T x) {
        Node<T> newNode = new Node<>(x, indexNode.prev, indexNode);
        newNode.prev.next = newNode;
        indexNode.prev = newNode;
        listSize++;
        modCount++;
    }

    /**
     * 对半法进行搜索
     * 获取的是一个node，包含了node的前驱，后驱，数据
     *
     * @param index
     * @return
     */
    private Node<T> getNode(int index) {
        Node<T> p;
        if (index < 0 || index > getListSize()) throw new IndexOutOfBoundsException();

        if (index < getListSize() / 2) {
            p = beginMarker.next;
            for (int i = 0; i < index; i++) {
                p = p.next;
            }
        } else {
            p = endMarker;
            for (int i = getListSize(); i > index; i--) {
                p = p.prev;
            }
        }
        return p;
    }

    public T remove(int index) {
        return remove(getNode(index));
    }

    /**
     * 链表的删除本质是链的打断和重连
     * a 《-> indexNode 《-> b
     * 把 a的后链指向 b， a(indexNode.prev） -> b(indexNode.next）
     * 把 b的前链指向 a， b(indexNode.next) 《- a（indexNode.prev）
     * 《->本质是引用
     * 这样 在链表里，虽然indexNode还引用了a和b，但是没有谁引用indexNode，它实际是可被回收的
     *
     * @param indexNode
     * @return
     */
    private T remove(Node<T> indexNode) {
        indexNode.prev.next = indexNode.next;
        indexNode.next.prev = indexNode.prev;
        listSize--;
        modCount++;
        return indexNode.data;
    }

    @Override
    public Iterator<T> iterator() {//实现MyList类的接口方法
        return new MyListIterator();
    }

    private class MyListIterator implements Iterator<T> {
        private Node<T> current = beginMarker.next;
        private int expectedModCount = modCount;
        private boolean okToRemove = false;

        @Override
        public boolean hasNext() {
            return current != endMarker;
        }

        @Override
        public T next() {
            if (modCount != expectedModCount) throw new ConcurrentModificationException();
            if (!hasNext()) throw new NoSuchElementException();
            T newItem = current.data;
            current = current.next;
            okToRemove = true;
            return newItem;
        }

        @Override
        public void remove() {
            if (modCount != expectedModCount) throw new ConcurrentModificationException();
            if (!okToRemove) throw new IllegalStateException();
            MyList.this.remove(current.prev);
            okToRemove = false;
            expectedModCount++;
        }
    }
}
