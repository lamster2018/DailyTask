package com.example.lahm.dailytask.Util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Project Name:DailyTask
 * Package Name:com.example.lahm.dailytask.Util
 * Created by lahm on 2017/6/17 下午5:05 .
 * <p>
 * 自定义处理logct信息,主要学习 android直接调用 shell
 */

public class LogUtil {
    public static void getLog() {
        System.out.println("--------func start--------"); // 方法启动
        try {
            ArrayList<String> cmdLine = new ArrayList<String>();   //设置命令   logcat -d 读取日志
            cmdLine.add("logcat");
            cmdLine.add("-d");

            ArrayList<String> clearLog = new ArrayList<String>();  //设置命令  logcat -c 清除日志
            clearLog.add("logcat");
            clearLog.add("-c");

            Process process = Runtime.getRuntime().exec(cmdLine.toArray(new String[cmdLine.size()]));   //捕获日志
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));    //将捕获内容转换为BufferedReader


//                Runtime.runFinalizersOnExit(true);
            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                //开始读取日志，每次读取一行
                Runtime.getRuntime().exec(clearLog.toArray(new String[clearLog.size()]));  //清理日志....这里至关重要，不清理的话，任何操作都将产生新的日志，代码进入死循环，直到bufferreader满
                if (str.contains("Starting a blocking GC Alloc"))
                    System.out.println("fuckyou" + str);
                //输出，在logcat中查看效果，也可以是其他操作，比如thrown uncaughtexception
            }
            if (str == null) {
                System.out.println("--   is null   --");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("--------func end--------");
    }
}

