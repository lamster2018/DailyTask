package com.example.LearnCallback;

/**
 * Project Name:DailyTask
 * Package Name:com.example.LearnCallback
 * Created by lahm on 2017/3/27 下午5:06 .
 * Copyright (c) 2017, www.footballzone.com All Rights Reserved.
 * <p>
 * 参考http://www.cnblogs.com/xrq730/p/6424471.html
 */

public class TestCallBack {
    public static void main(String[] args) {
        Student Fake = new Student("Fake");

        Teacher teacherLi = new Teacher();
        teacherLi.askQuestion(Fake);
    }
}
