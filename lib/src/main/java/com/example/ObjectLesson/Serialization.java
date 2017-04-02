package com.example.ObjectLesson;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;

/**
 * Project Name:DailyTask
 * Package Name:com.example.ObjectLesson
 * Created by lahm on 2017/4/2 下午5:09 .
 * <p>
 * https://github.com/lamster2018
 * 简单地说，就是可以将一个对象(标志对象的类型)及其状态转换为字节码，
 * 保存起来（可以保存在数据库，内存，文件等）,(也就是反序列化)。
 * serialization 不但可以在本机做，而且可以经由网络操作。
 * 它自动屏蔽了操作系统的差异，字节顺序等，
 * 不必关心数据在不同机器上如何表示，也不必关心字节的顺序或者其他任何细节。
 */

public class Serialization {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String filePath = "/Users/lahm/Desktop/OOS.asUlike";
        File file = new File(filePath);
        if (!file.exists()) file.createNewFile();
        //前期准备一个空白文件，后缀as u like供序列化输出写操作

        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream out = new ObjectOutputStream(fos);
        //创建一个对象输出流，此处包装的是文件输出流
        //序列化操作
        out.writeObject("string");
        out.writeObject(new Date());
        SerializableDataMode mode = new SerializableDataMode(20, "Amarni","mima");
        out.writeObject(mode);
        out.writeInt(007);//写入基本类型数据
        out.close();//写操作，关流

        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream in = new ObjectInputStream(fis);
        //对象输入流，此处包装一个文件输入流
        //反序列化操作
        System.out.println("obj1  " + in.readObject());//可以不用强转型
        System.out.println("obj2  " + (Date) in.readObject());//也可强转型
        System.out.println("obj3  " + in.readObject().toString());
        System.out.println("int   " + in.readInt());
        in.close();//读操作顺序，也就是反序列化的顺序要和序列化的一致，不然类型对不上，就运行报错
    }
}
