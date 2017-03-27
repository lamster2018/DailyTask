package com.example.LearnCallback;

/**
 * Project Name:DailyTask
 * Package Name:com.example.LearnCallback
 * Created by lahm on 2017/3/27 下午4:46 .
 * Copyright (c) 2017, www.footballzone.com All Rights Reserved.
 * <p>
 * 不同学生有不同的名字
 * 每个学生都有思考的功能（Thinking接口）
 * 思考的时候进行处理，思考完以后，回调
 */

public class Student implements Thinking {
    String name;

    public Student(String name) {
        this.name = name;
    }

    @Override
    public void thinking(Question question) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            question.tellAnswer("callback success");
        }
    }
}