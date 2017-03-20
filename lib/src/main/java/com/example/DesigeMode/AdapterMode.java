package com.example.DesigeMode;

/**
 * Project Name:DailyTask
 * Package Name:com.example.DesigeMode
 * Created by lahm on 2017/3/20 上午11:21 .
 * Copyright (c) 2017, www.footballzone.com All Rights Reserved.
 * 适配器模式在设计模式体系中属于结构型模式，
 * 可以分为三类：类的适配器模式、对象的适配器模式和接口的适配器模式，
 * 由于类和接口的适配器模式需要适配器继承原有的类，
 * 而纯面向对象语言Java、C#都是不支持多继承的，
 * 这在一定程度上制约了类和接口的适配器模式的使用场景，故使用的时候要注意。
 * <p>
 * 使用目的：将一个类的接口转换成客户希望的另外一个接口。
 * 适配器模式使得原本由于接口不兼容而不能一起工作的那些类可以一起工作。
 * http://www.2cto.com/kf/201312/268761.html
 */

public class AdapterMode {
    //因为是单独的运行类，所以每个都加上了static
    public static void main(String args[]) {
        //类（继承）适配
        ClassAdapter classAdapter = new ClassAdapter();
        classAdapter.oldFunction();
        classAdapter.newFunction();
        //对象适配，构造的时候把原始类的引用传进去了
        Source source = new Source();
        ObjectAdapter objectAdapter = new ObjectAdapter(source);
        objectAdapter.oldFunction();
        objectAdapter.newFunction();
        //接口适配
        InterfaceAdapter interfaceAdapter = new InterfaceAdapter();
        interfaceAdapter.oldFunction();
        interfaceAdapter.newFunction1();
        interfaceAdapter.newFunction2();

    }

    static class Source {
        public void oldFunction() {
            System.out.println("oldFunction:Source");
        }
    }

    interface Target {
        void oldFunction();

        // 新接口
        void newFunction();
    }

    interface ManyOtherTarget {
        void oldFunction();

        // 新接口
        void newFunction1();

        void newFunction2();

        void newFunction3();
    }

    // 类适配器，用来做接口转换，最简单，
    // 但是如果target接口里又添加新的x方法，
    // 可能A类需要，而B类并不需要，还是会被迫复写x方法
    static class ClassAdapter extends Source implements Target {

        public ClassAdapter() {
            super();
        }

        @Override
        public void newFunction() {
            System.out.println("newFunction:ClassAdapter");
        }
    }

    // 对象适配器，用来做接口转换，旧方法就直接让旧类去调用，
    // 不过也存在类适配的缺点
    static class ObjectAdapter implements Target {

        // 适配器内部持有的原始类对象
        private Source mSource;

        public ObjectAdapter(Source source) {
            mSource = source;
        }

        @Override
        public void oldFunction() {
            // 这里调用原始类中的方法
            mSource.oldFunction();
        }

        @Override
        public void newFunction() {
            System.out.println("newFunction:ObjectAdapter");
        }
    }

    // 接口适配器，继承TargetWrapper，重写自己感兴趣的方法，
    // 规避以上两种的缺点，实际上是分两步走，因为接口的定义，实现接口必然要复写方法，
    // 所以实际在其父抽象类还是复写了，验证方式:屏蔽接口里的某方法，会在下面报编译错误
    static class InterfaceAdapter extends TargetWrapper {

        public InterfaceAdapter() {
            super();
        }

        @Override
        public void newFunction2() {
            System.out.println("only change func2");
        }
    }

    //说明：也叫缺省适配器模式，
    // 主要解决接口的复用问题：有时候可能我们的业务只需要使用接口中的某一个方法而不是全部方法，
    // 但是由于接口的语言特性而不得不实现全部的抽象方法，
    // 这样就会使得接口的使用过程很麻烦，特别是接口中存在很多抽象方法的时候。
    // 面对接口的这类问题，我们可以采用一个抽象类（也可以不是抽象类）去实现接口，
    // 然后让我们的类去继承这个抽象类同时只重写我们感兴趣的方法即可。
    static abstract class TargetWrapper implements ManyOtherTarget {
        @Override
        public void oldFunction() {
            System.out.println("oldFunction");
        }

        @Override
        public void newFunction1() {
            System.out.println("func1");
        }

        @Override
        public void newFunction2() {
            System.out.println("func2");
        }

        @Override
        public void newFunction3() {
            System.out.println("func3");
        }
    }

}
