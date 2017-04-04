package com.example.DesignMode;

/**
 * Project Name:DailyTask
 * Package Name:com.example.DesignMode
 * Created by lahm on 2017/3/20 下午12:32 .
 * https://github.com/lamster2018
 * http://blog.csdn.net/beyond0525/article/details/22794221
 */

public class FactoryMode {

    public static void main(String[] args) {
        //简单工厂模式
//        SingleFactory factory = new SingleFactory();
//        Sender sender = factory.produce("sms");
//        sender.Send();
        //多工厂模式
//        MultiFactory factory = new MultiFactory();
//        Sender sender = factory.produceMail();
//        sender.Send();
        //静态工厂模式,好处就是无需实例化一个工厂类
        Sender sender = StaticFactory.produceStaticFunc();
        sender.Send();
        //抽象工厂模式
//        Factory whichFac = new AFac();//找到一个工厂
//        FacFunc whichFunc = whichFac.provide();//工厂能提供什么流水线
//        whichFunc.product();//什么流水线提供什么产品
    }

    public interface Sender {
        public void Send();
    }

    public class MailSender implements Sender {
        @Override
        public void Send() {
            System.out.println("this is mailsender!");
        }
    }

    public class SmsSender implements Sender {
        @Override
        public void Send() {
            System.out.println("this is sms sender!");
        }
    }

    public static class StaticSender implements Sender {
        @Override
        public void Send() {
            System.out.println("this is static sender!");
        }
    }

    //简单工厂模式
    public class SingleFactory {
        public Sender produce(String type) {
            if ("mail".equals(type)) {
                return new MailSender();
            } else if ("sms".equals(type)) {
                return new SmsSender();
            } else {
                System.out.println("请输入正确的类型!");
                return null;
            }
        }
    }

    //多工厂模式
    public class MultiFactory {
        public Sender produceMail() {
            return new MailSender();
        }

        public Sender produceSms() {
            return new SmsSender();
        }
    }

    //静态工厂模式，就是在多工厂的方式上加个static，无需实例化
    public static class StaticFactory {
        public static Sender produceStaticFunc() {
            return new StaticSender();
        }
    }

    //抽象工厂模式，这个有点难理解
    //AB工厂实现Factory接口--》不同工厂有不同的流水线FacFunc
    //FacFunc接口提供provide不同的产品；
    //至于产品里干嘛，由产品自己描述；
    //这样新增工厂，只需要工厂自己实现Factory接口，生产产品即可，
    // 而不需要动产品线的方法
    interface FacFunc {
        public void product();
    }

    public class XPro implements FacFunc {
        @Override
        public void product() {
            System.out.println("生产了X产品");
        }
    }

    public class YPro implements FacFunc {
        @Override
        public void product() {
            System.out.println("生产了Y产品");
        }
    }

    interface Factory {
        public FacFunc provide();
    }

    public class AFac implements Factory {
        @Override
        public FacFunc provide() {
            return new XPro();
        }
    }

    public class BFac implements Factory {
        @Override
        public FacFunc provide() {
            return new YPro();
        }
    }
}
