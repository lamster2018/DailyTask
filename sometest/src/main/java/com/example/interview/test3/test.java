package com.example.interview.test3;

/**
 * Project Name:DailyTask
 * Package Name:com.example.interview.test3
 * Created by lahm on 2017/4/26 下午5:09 .
 * <p>
 * https://github.com/lamster2018
 */

public class test {
    private static String s1 = "()[]{}";
    private static String s2 = "([)]";

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.isValid(s1));//---true
        System.out.println(solution.isValid(s2));//---false
        System.out.println(solution.change10To62(10));//---A
        System.out.println(solution.change62To10("10"));//62
    }
}
