package com.example.lahm.dailytask.NDK;

/**
 * Project Name:DailyTask
 * Package Name:com.example.lahm.dailytask.NDK
 * Created by lahm on 2018/3/7 下午3:19 .
 * 参考ijk的loadLib 方法
 */

public interface LibLoader {
    void loadLibrary(String libName) throws UnsatisfiedLinkError, SecurityException;
}
