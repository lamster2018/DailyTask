package com.example.interview.test1;

/**
 * 考察了
 * 1.接口，如果两个接口，里面有一模一样的方法，实现会不会冲突？
 * 2.继承里，构造器和代码块的调用顺序/子类和父类构造器的关系
 * 3.内部类的调用
 */
public class MyClass implements interfaceB, interfaceA {

    @Override
    public void sum() {

    }

    @Override
    public int sub(int i) {
        return 0;
    }

    public static void main(String[] args) {
//        classB b = new classB(1);
//        System.out.println(b.i);
//        classA a = new classA();
//        classA.innerClassC c = a.new innerClassC();
//        c.print();
        printStar(5);
    }

    // print star like
    // *****
    //  ****
    //   ***
    //    **
    //     *

    private static void printStar(int n) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < n; i++) {//control 排
            for (int j = 0; j < n; j++) { //control 个数
                if (j < i) sb.append(" ");
                else sb.append("*");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

}
