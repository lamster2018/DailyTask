package com.example.ObjectLesson;

import java.io.Serializable;

/**
 * Project Name:DailyTask
 * Package Name:com.example.ObjectLesson
 * Created by lahm on 2017/4/2 下午5:18 .
 * <p>
 * https://github.com/lamster2018
 */

public class SerializableDataMode implements Serializable {
    private int age;
    private String name;
    private transient String pwd;
//    Java的serialization提供了一种持久化对象实例的机制。
//    当持久化对象时，可能有一个特殊的对象数据成员，我们不想用serialization机制来保存它。
//    为了在一个特定对象的一个域上关闭serialization，可以在这个域前加上关键字transient。
//    transient是Java语言的关键字，用来表示一个域不是该对象串行化的一部分。
//    当一个对象被串行化的时候，transient型变量的值不包括在串行化的表示中，
//    然而非transient型的变量是被包括进去的。
//    注意static变量也是可以串行化的

    SerializableDataMode(int age, String name, String pwd) {
        this.age = age;
        this.name = name;
        this.pwd = pwd;
    }

    //重写对象里的toString方法，方便打印
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        return sb.append(age)
                .append(" ")
                .append(name)
                .append(" ")
                .append(pwd)
                .toString();
    }
}
