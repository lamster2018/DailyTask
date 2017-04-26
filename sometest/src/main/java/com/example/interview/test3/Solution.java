package com.example.interview.test3;

import java.util.ArrayList;

/**
 * Project Name:DailyTask
 * Package Name:com.example.interview.test3
 * Created by lahm on 2017/4/26 下午5:01 .
 * <p>
 * https://github.com/lamster2018
 */

public class Solution {

    //1、给定一个字符串所表示的括号序列，包含以下字符： '(', ')', '{', '}', '[' and ']'， 判定是否是有效的括号序列。
    // 括号必须依照 "()" 顺序表示， "()[]{}" 是有效的括号，但 "([)]"则是无效的括号。

    public static Boolean isValid(String value) {
        int count = 0;
        for (int i = 0, len = value.length(); i < len; i++) {
            switch (value.charAt(i)) {
                case '(':
                    count++;
                    break;
                case ')':
                    count--;
                    break;
                case '[':
                    count++;
                    break;
                case ']':
                    count--;
                    break;
                case '{':
                    count++;
                    break;
                case '}':
                    count--;
                    break;
                default:
                    break;
            }
            if (count > 1 || count < 0) return false;
        }
        return true;
    }

    //2、用0-9a-zA-Z实现一个62进制
    //实现从10进制转换成62进制方法（例如：121 -> “1X”）
    //实现从62进制转换成10进制方法（例如：”1X” -> 121）

    public String change10To62(int n) {
        if (n < 0) return "error";
        int value = n;
        int result = 0;
        ArrayList<String> arrayList = new ArrayList<>();
        StringBuffer sb = new StringBuffer();
        while (value != 0) {
            value = value / 62;
            result = n % 62;
            if (result < 10)
                arrayList.add(String.valueOf(result));
            else if (result >= 10 || result < 36)
                arrayList.add(0, String.valueOf((char) (result + 55)));
            else if (result >= 36 || result < 62)
                arrayList.add(0, String.valueOf((char) (result + 61)));
            else continue;
        }
        for (int i = 0, len = arrayList.size(); i < len; i++) {
            sb.append(arrayList.get(i));
        }
        return sb.toString();
    }

    public int change62To10(String value) {
        int result = 0;
        int temp;
        for (int len = value.length(), i = len - 1; i >= 0; i--) {
            temp = (Integer.valueOf(value.charAt(i)) - 48) * 62;
            result += temp;
            temp = 0;
        }
        return result;
    }


//3、The count-and-say sequence is the sequence of integers beginning as follows:1, 11, 21, 1211, 111221, ...
//            1 is read off as "one 1" or 11.
//            11 is read off as "two 1s" or 21.
//            21 is read off as "one 2, then one 1" or 1211.
//    Given an integer n, generate the nth sequence.
//            Note: The sequence of integers will be represented as a string.


//    public String countAndSay(int n) {
//
//    }

//    4、Given a board with m by n cells, each cell has an initial state live (1) or dead (0). Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the following four rules:
//            1. Any live cell with fewer than two live neighbors dies, as if caused by under-population.
//  2. Any live cell with two or three live neighbors lives on to the next generation.
//  3. Any live cell with more than three live neighbors dies, as if by over-population..
//            4. Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
//    Write a function to compute the next state (after one update) of the board given its current state. This is Game of Life.

    public void gameOfLife(int[][] board) {

    }
}
