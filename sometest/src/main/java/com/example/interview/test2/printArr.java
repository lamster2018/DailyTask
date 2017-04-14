package com.example.interview.test2;

/**
 * Project Name:DailyTask
 * Package Name:com.example.interview1.interview2
 * Created by lahm on 2017/4/12 上午8:59 .
 * <p>
 * https://github.com/lamster2018
 * <p>
 * 从中心开始，逆时针打印n*n矩阵
 * 例如：
 * <p>
 * [  0,  1,  2,  3]
 * [  4,  5,  6,  7]
 * [  8,  9, 10, 11]
 * [ 12, 13, 14, 15]
 * <p>
 * <p>
 * 输出：
 * <p>
 * 6,5,9,10,11,7,3,2,1,0,4,8,12,13,14,15
 */

public class printArr {
    public static void main(String[] args) {
        int n = 6;
        int[][] arr = new int[n][n];
        arr[0] = new int[]{0, 1, 2, 3, 4, 5};
        arr[1] = new int[]{6, 7, 8, 9, 10, 11};
        arr[2] = new int[]{12, 13, 14, 15, 16, 17};
        arr[3] = new int[]{18, 19, 20, 21, 22, 23};
        arr[4] = new int[]{24, 25, 26, 27, 28, 29};
        arr[5] = new int[]{30, 31, 32, 33, 34, 35};
        printReverse(arr);
//        print(arr);
    }

    //难点在，判断何时换向
    private static void printReverse(int[][] arr) {
        int row = arr.length;//行
        int col = arr[0].length;//列
        //一维数组直接打印
        if (row == 1) {
            System.out.println(arr[0][0]);
            return;
        }
        // 坐标系以0，0为原点，向下正向，向右正向
        // 定义初始方向是向上
        int directX = 0;//-1向←移动，0该方向不变
        int directY = -1;//-1向上移动,0该方向不变
        // 开始坐标
        int xStart = row / 2;//偶数个取靠后的列
        int yStart = col % 2 == 0 ? col / 2 - 1 : col / 2;//偶数个时，取上一排
        //边界值
        int xEnd = row - 1;
        int yEnd = col - 1;
        // 总循环次数
        int sum = row * col;
        // 计数器
        int count = 0;
        // 标记数组，用来check
        boolean[][] checkArr = new boolean[row][col];
        // 优先拐弯
        while (count < sum) {
            System.out.println(arr[yStart][xStart]);
            checkArr[yStart][xStart] = true;//走过的位置做个标记
            if (directX < 0) {
                //只向左平移, 竖直方向为0
                if (directX + xStart < 0 || !checkArr[yStart + 1][xStart]) {
                    //满足条件变向下
                    directX = 0;
                    directY = 1;
                }
            } else if (directX > 0) {
                //只向右平移
                if (directX + xStart > xEnd || !checkArr[yStart - 1][xStart]) {
                    //满足条件变向上
                    directX = 0;
                    directY = -1;
                }
            } else if (directY < 0) {
                //只向上平移
                if (directY + yStart < 0 || !checkArr[yStart][xStart - 1]) {
                    //满足条件变向左
                    directY = 0;
                    directX = -1;
                }
            } else if (directY > 0) {
                //只向下平移
                if (directY + yStart > yEnd || !checkArr[yStart][xStart + 1]) {
                    //满足条件变向右
                    directY = 0;
                    directX = 1;
                }
            } else {
                directX = 0;
                directY = 0;
            }
            yStart += directY;
            xStart += directX;
            count++;
        }
    }

    private static void print(int[][] arr) {
        for (int i = 0, len = arr.length; i < len; i++) {
            for (int j = 0, len2 = arr[i].length; j < len2; j++) {
                System.out.print(arr[i][j]);
            }
        }
    }
}
