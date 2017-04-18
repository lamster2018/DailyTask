package com.example.lahm.dailytask.OpenGL;

import android.opengl.GLSurfaceView;
import android.opengl.GLU;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Project Name:DailyTask
 * Package Name:com.example.lahm.dailytask.OpenGL
 * Created by lahm on 2017/4/17 下午5:06 .
 * <p>
 * https://github.com/lamster2018
 */

class MyRenderer implements GLSurfaceView.Renderer {
    // Initialize our square.
    Square square = new Square();

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        // 设置清屏颜色RGBA
        gl.glClearColor(0f, 1f, 0f, 0f);//green
        gl.glDisable(GL10.GL_DITHER);//关闭抗抖动
        // 设置阴影平滑, default not really needed.
        gl.glShadeModel(GL10.GL_SMOOTH);
        // Depth buffer setup.
        gl.glClearDepthf(1.0f);
        // 允许深度测试，实际就是openGL去计算z轴,不让后边的物体遮挡前面的
        gl.glEnable(GL10.GL_DEPTH_TEST);
        // 深度测试方法
        gl.glDepthFunc(GL10.GL_LEQUAL);
        // 设置系统对透视进行修正
        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT,
                GL10.GL_NICEST);//Hint,对某方面进行修正
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        // 设置3D视窗初始位置和大小
        gl.glViewport(0, 0, width, height);// OpenGL docs.
        //  设置矩阵模式为投影模式
        gl.glMatrixMode(GL10.GL_PROJECTION);// OpenGL docs.
//        // 恢复矩阵，类似canvas恢复画板
        gl.glLoadIdentity();// OpenGL docs.
//        // 计算投影坐标系
        GLU.gluPerspective(gl, 45.0f, (float) width / (float) height, 0.1f, 100.0f);
//        // 设置矩阵模式？不知道这里为什么要第二次设置
        gl.glMatrixMode(GL10.GL_MODELVIEW);// OpenGL docs.
        gl.glLoadIdentity();// OpenGL docs.
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        // 清除屏幕缓存和深度缓存
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();
        gl.glTranslatef(0, 0, -4);
        square.draw(gl);
    }
}
