package com.example.lahm.dailytask.OpenGL;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;

/**
 * Project Name:DailyTask
 * Package Name:com.example.lahm.dailytask.OpenGL
 * Created by lahm on 2017/4/18 下午4:21 .
 * <p>
 * https://github.com/lamster2018
 */

public class BufferUtil {
    public static IntBuffer int2IntBuffer(int[] arr) {
        IntBuffer intBuffer;
        // 初始化ByteBuffer,长度是arr数组的长度的4倍，因为一个int占4个字节
        ByteBuffer bb = ByteBuffer.allocateDirect(arr.length * 4);
        // 数组排列用nativeOrder
        bb.order(ByteOrder.nativeOrder());
        intBuffer = bb.asIntBuffer();
        intBuffer.put(arr);
        intBuffer.position(0);
        return intBuffer;
    }

    public static FloatBuffer float2FloatBuffer(float[] arr) {
        FloatBuffer floatBuffer;
        // 初始化ByteBuffer,长度是arr数组的长度的4倍，因为一个float占4个字节
        ByteBuffer bb = ByteBuffer.allocateDirect(arr.length * 4);
        bb.order(ByteOrder.nativeOrder());
        floatBuffer = bb.asFloatBuffer();
        floatBuffer.put(arr);
        floatBuffer.position(0);
        return floatBuffer;
    }

    public static ShortBuffer short2ShortBuffer(short[] arr) {
        ShortBuffer shortBuffer;
        // 初始化ByteBuffer,长度是arr数组的长度的4倍，因为一个short占2个字节
        ByteBuffer bb = ByteBuffer.allocateDirect(arr.length * 2);
        bb.order(ByteOrder.nativeOrder());
        shortBuffer = bb.asShortBuffer();
        shortBuffer.put(arr);
        shortBuffer.position(0);
        return shortBuffer;
    }
}
