package com.example.ObjectLesson;

/**
 * Project Name:DailyTask
 * Package Name:com.example.ObjectLesson
 * Created by lahm on 2017/4/2 上午9:12 .
 * <p>
 * https://github.com/lamster2018
 * 对象三大特征之多态,这里图方便只建了一个文件，所以类全是static，绝不影响最终结果
 * 多态的意思就是
 * 1.引用变量会指向哪个类的实例对象，
 * 2.该引用变量具体调用的是哪个类的方法
 * 编译时不确定，运行时才能确定，两者不一定一样。
 * 实现多态有三个必要条件：继承，重写，向上转型
 */

public class Polymorphism {
    public static void main(String[] args) {
        A a1 = new A();
        A a2 = new B();
        B b = new B();
        C c = new C();
        D d = new D();

        System.out.println("1--" + a1.show(b));//1--A and A
        System.out.println("2--" + a1.show(c));//2--A and A
        System.out.println("3--" + a1.show(d));//3--A and D
        //声明的a1这个指针变量，指向的就是A，a1可看做A的实例对象，
        //关键在，A的方法里没有B,C做形参的方法，但是BC是A的子类，所以向上转型了
        //而A里有D做形参的方法，所以不必转型，直接调用即可
        //验证方法:A里加上BC做形参的方法，运行会打印各自的类名
        //其实这里结合4--的优先级调用顺序，可解释为
        //A来决定，this是A，A无showBC（1级），A无父类（2级）
        //b和c都有父类A，所以就找了this.showA
        //this有showD，直接showD
        //这样解释比较易懂

        System.out.println("4--" + a2.show(b));//4--B and A
        //超类对象（A）引用变量（a2） 引用子类对象时，由被引用对象（B）的类型决定调用谁的成员方法，
        //而不是由引用变量(a2)的类型决定，但是被调用的方法必须是超类（A）中定义过的，也就是子类（B）要super重写该方法
        //验证方法：超类A里添加show（B obj）方法，输出B and B
        //最终方法的调用顺序，是由继承链中对象方法的调用优先级选择：
        // this.show--super.show--this.show((super)Obj)--super.show((super)Obj)
        //这里a2是A的引用变量，所以this是A，A里没有showB方法this.show，A也没有父类（Object不算）super.show，
        // 所以看第三级,b是B类的实例对象，B有一个父类A，也就是super A，this也是A，也就是找到了A.show(A)方法，
        // 谁调用showA方法呢？第一句话，由被引用的类型决定，B刚好重写了showA，所以最终了BshowA
        //验证方法:去掉BshowA，B自己解决不了，那只能决定让给父类，输出是 AshowA,此时就是第四级，super.show(super)
        System.out.println("5--" + a2.show(c));//5--B and A
        //仍旧是B来决定调用谁的成员方法，show（C）在B类里没有，去找超类A，A也没有，看第三级，C的超类有B->A,this是A
        //找到了this.showA,B来决定调谁的，B重写了showA，所以BshowA
        //验证方法，A中有showC，由于B没有showC，输出AshowC,如果在B中添加showC，则会输出BshowC
        System.out.println("6--" + a2.show(d));//6--A and D
        //同样的，B来决定，B无showD，找优先级this是A，A里有showD，直接就AshowD
        //验证方法，B添加showD，B这就是复写了父类AshowD，直接输出BshowD
        System.out.println("7--" + b.show(b)); //7--B and B
        //引用类型是B，B来决定，this也是B，B中有showB方法，直接输出
        System.out.println("8--" + b.show(c)); //8--B and B
        //B来决定，this是B，B无showC，B的父类A也无showC，形参C父类是B—>A,所以先看this.showB，正好有
        System.out.println("9--" + b.show(d)); //9--A and D
        //B来决定，this是B，B无D，B的父类A有showD，B无showD，所以就输出AshowD，
        //验证方法：添加BshowD，父类有，子类重写，调用子类的showD

    }

    public static class A {
        public String show(A obj) {
            return "A and A";
        }

//        public String show(B obj) {
//            return "A and B";
//        }

//        public String show(C obj) {
//            return "A and C";
//        }

        public String show(D obj) {
            return "A and D";
        }
    }

    public static class B extends A {

        public String show(A obj) {
            return "B and A";
        }

        public String show(B obj) {
            return "B and B";
        }

//        public String show(C obj) {
//            return "B and C";
//        }

//        public String show(D obj) {
//            return "B and D";
//        }
    }

    public static class C extends B {

    }

    public static class D extends B {

    }
}
