package com.example.DesigeMode;

/**
 * Project Name:DailyTask
 * Package Name:com.example.DesigeMode
 * Created by lahm on 2017/3/20 下午1:24 .
 * Copyright (c) 2017, www.footballzone.com All Rights Reserved.
 * 设计模式分为三大类：
 * 创建型模式，共五种：工厂方法模式、抽象工厂模式、单例模式、建造者模式、原型模式。
 * 结构型模式，共七种：适配器模式、装饰器模式、代理模式、外观模式、桥接模式、组合模式、享元模式。
 * 行为型模式，共十一种：策略模式、模板方法模式、观察者模式、迭代子模式、责任链模式、命令模式、备忘录模式、状态模式、访问者模式、中介者模式、解释器模式。
 * 其实还有两类：并发型模式和线程池模式。
 * <p>
 * http://zz563143188.iteye.com/blog/1847029
 */

public class BuilderMode {
    public String computerName;
    public String computerCpu;
    public int computerSSD;
    public String computerMainBoard;

    public void show() {
        System.out.println(computerName + " "
                + computerCpu + " "
                + computerSSD + " "
                + computerMainBoard);
    }

    //参考AlertDialog的写法，优点在，我可以明显的看到，我想设置的组件
    //至于内部怎么组装的我不关心，AlertDialog的Alert.Builder的create方法
    //调用了AlertController的apply方法，实际上就是createComputer的组装
    //我只关系我设置了哪些，然后告诉一下可以组装了。
    //实际，也可以BuilderMode直接setValue，只不过返回类型为BuilderMode本身，而不是Builder
    public static class Builder {
        public String name;
        public String cpu;
        public int SSD;
        public String mainBoard;

        public Builder(String computerName) {
            this.name = computerName;
        }

        public Builder setCpu(String cpu) {
            this.cpu = cpu;
            return this;
        }

        public Builder setSSD(int SSDSize) {
            this.SSD = SSDSize;
            return this;
        }

        public Builder setMainBoard(String mainBoard) {
            this.mainBoard = mainBoard;
            return this;
        }

        public BuilderMode createComputer() {
            BuilderMode builderMode = new BuilderMode();
            builderMode.computerName = name;
            builderMode.computerCpu = cpu;
            builderMode.computerSSD = SSD;
            builderMode.computerMainBoard = mainBoard;
            return builderMode;
        }
    }
}
