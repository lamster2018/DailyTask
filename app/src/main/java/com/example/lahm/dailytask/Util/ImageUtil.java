package com.example.lahm.dailytask.Util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;

import java.io.InputStream;

/**
 * Project Name:Dailytask
 * Package Name:com.example.lahm.dailytask.Util
 * Created by lahm on 2017/8/20 下午5:15 .
 * <p>
 * Copyright (c) 2016—2017 https://www.lizhiweike.com all rights reserved.
 */

public class ImageUtil {
    /**
     * 以最省内存的方式读取本地资源的图片，会以为机器分辨率不同而无法适配
     * http://blog.csdn.net/woshicaixianfeng/article/details/6825295
     * http://bbs.csdn.net/topics/390919272
     * 如何回收这些资源
     * http://blog.csdn.net/springsky_/article/details/25212419
     *
     * @param c
     * @param r
     * @return BitmapDrawable
     */
    public static BitmapDrawable setBackground(Context c, int r) {
        Bitmap bitmap = readBitMap(c, r);
        BitmapDrawable bd = new BitmapDrawable(bitmap);
// bd.setTileModeXY(Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
        bd.setDither(true);
        return bd;
    }

    /**
     * 以最省内存的方式读取本地资源的图片
     *
     * @param context
     * @param resId
     * @return Bitmap
     */
    public static Bitmap readBitMap(Context context, int resId) {
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inPreferredConfig = Bitmap.Config.RGB_565;
        opt.inPurgeable = true;
        opt.inInputShareable = true;
// 获取资源图片
        InputStream is = context.getResources().openRawResource(resId);
        return BitmapFactory.decodeStream(is, null, opt);
    }
}

