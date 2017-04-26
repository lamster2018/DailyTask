package com.example.interview.test1;

/**
 * Project Name:DailyTask
 * Package Name:com.example
 * Created by lahm on 2017/4/11 下午6:04 .
 * <p>
 * https://github.com/lamster2018
 */

public interface interfaceB {
    int sub(int i);//A 借口也有这个，但是只要实现其中一个就行

    int sub(String i);
    //其实就是函数重载的一个体现，
    // 不以形参名称or返回值类型做判断
    //而是以形参个数&形参类型做判断
}
