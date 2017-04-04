package com.example.DesignMode;

/**
 * Project Name:DailyTask
 * Package Name:com.example.DesignMode
 * Created by lahm on 2017/4/4 下午3:38 .
 * <p>
 * https://github.com/lamster2018
 * 策略者模式，由类本身实现具体方法，封装好，至于外部用哪个方法，不管。
 */

public class Strategy {

    public interface ICalFunc {
        public String calFunc();
    }

    public class Plus implements ICalFunc {

        @Override
        public String calFunc() {
            return "+++++++";
        }
    }

    public class Subs implements ICalFunc {

        @Override
        public String calFunc() {
            return "-------";
        }
    }

    public static void main(String[] a) {
//        ICalFunc cal = new Plus();
//        cal.calFunc();
    }
}
