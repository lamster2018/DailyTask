package com.example.interview1;

/**
 * Project Name:DailyTask
 * Package Name:com.example
 * Created by lahm on 2017/4/11 下午6:06 .
 * <p>
 * https://github.com/lamster2018
 */

public class classA {
    {
        System.out.println("l");
    }

    classA() {
        System.out.println("A");
    }

    static int i = 20;

    //外部类可以直接像普通的方式声明调用类一样，直接new
    //非外部类，想调用某外部类的内部类，需要声明完整包名，且必须要有外部类的实例做桥梁
    //即 classA.innerClassC ac = new classA().new innerClassC();
    class innerClassC {
        innerClassC() {
            System.out.println("C");
        }

        public void print() {
            System.out.println("CC");
        }
    }

    static {
        System.out.println("m");
    }

    {
        System.out.println("n");
    }
}
