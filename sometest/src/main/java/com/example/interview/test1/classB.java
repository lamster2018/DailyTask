package com.example.interview.test1;

/**
 * Project Name:DailyTask
 * Package Name:com.example
 * Created by lahm on 2017/4/11 下午6:07 .
 * <p>
 * https://github.com/lamster2018
 */

public class classB extends classA {


    {
        System.out.println("z");
    }

    // 构造器，继承很有意思的
    // classA只有默认构造器，子类的构造器都会隐式调用super();
    // 也就是无论子类classB的构造器是否有参，实际上都隐式调用了classA的默认构造器;
    // 如果classA写了构造器 classA（int a），
    // 那么classB必须要有classB（int b），并且要显式调用super（num）
    // 此时classB如果要加上空构造器classB（），父类必须要要有个classA（）空构造器，
    // 此时classB的空构造器隐式调用super（）；
    classB(int num) {
//        super(num);//隐式调用了super()
        System.out.println("B");
    }

    //代码块优先顺序，同一层级按代码中出现顺序
    // 父类静态代码块 -》子类静态代码块 -》
    // 父类非静态代码块 -》父类构造器 -》
    // 子类非静态代码块 -》子类构造器

    //如果父类没有特别写明 默认构造器，那么父类静态代码块不执行，
    static {
        System.out.println(classB.i);
        System.out.println("x");
    }

    {
        System.out.println("y");
    }

}
