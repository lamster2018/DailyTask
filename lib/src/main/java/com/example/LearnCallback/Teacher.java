package com.example.LearnCallback;

/**
 * Project Name:DailyTask
 * Package Name:com.example.LearnCallback
 * Created by lahm on 2017/3/27 下午4:42 .
 * https://github.com/lamster2018
 * <p>
 * 不同的老师都有问问题的功能（也就是Question接口）
 * 不同老师也可以问不同的学生,askQuestion(differentStudent)
 * 最后学生思考后，老师会说：  告诉我答案吧
 */

public class Teacher implements Question {

    public void askQuestion(Student student) {
        student.thinking(this);
    }

    @Override
    public void tellAnswer(String answer) {
        System.out.println("The answer is " + answer);
    }
}
