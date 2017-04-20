package com.example.lahm.dailytask.OpenGL;

import android.app.ActivityManager;
import android.content.pm.ConfigurationInfo;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.lahm.dailytask.BaseActivity;

/**
 * Project Name:DailyTask
 * Package Name:com.example.lahm.dailytask.OpenGL
 * Created by lahm on 2017/4/17 下午4:58 .
 * <p>
 * https://github.com/lamster2018
 * http://wiki.jikexueyuan.com/project/opengl-es-basics/opengl-es-view.html
 */

public class TestOpenGLActivity extends BaseActivity
        implements GestureDetector.OnGestureListener {
    private GLSurfaceView glSurfaceView;
    // 定义旋转角
    private float anglex = 0f;
    private float angley = 0f;
    static final float ROTATE_FACTOR = 60;
    // 创建渲染器实例
    private MyRenderer mRenderer = new MyRenderer();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //如果本设备支持OpenGl ES 2.0
        if (isSupportGL()) {
            //新建GLSurfaceView实例
            glSurfaceView = new GLSurfaceView(this);
            // 设置渲染器
            glSurfaceView.setRenderer(mRenderer);
            //设置渲染模式
            glSurfaceView.setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
            //显示SurfaceView
            setContentView(glSurfaceView);
        } else
            Toast.makeText(this, "no-support", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (glSurfaceView != null) glSurfaceView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (glSurfaceView != null) glSurfaceView.onPause();
    }

    private boolean isSupportGL() {
        ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        ConfigurationInfo configurationInfo = activityManager.getDeviceConfigurationInfo();
        boolean isSupport = configurationInfo.reqGlEsVersion >= 0x2000;
        Log.i("openGLVersion", "isSupportGL: " + configurationInfo.getGlEsVersion()
                + "   " + isSupport);
        return isSupport;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        velocityX = velocityX > 2000 ? 2000 : velocityX;
        velocityX = velocityX < -2000 ? -2000 : velocityX;
        velocityY = velocityY > 2000 ? 2000 : velocityY;
        velocityY = velocityY < -2000 ? -2000 : velocityY;
        angley += velocityX * ROTATE_FACTOR / 4000;
        anglex += velocityY * ROTATE_FACTOR / 4000;
        return true;
    }
}
