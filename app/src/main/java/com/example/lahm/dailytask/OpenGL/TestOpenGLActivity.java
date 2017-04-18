package com.example.lahm.dailytask.OpenGL;

import android.app.ActivityManager;
import android.content.pm.ConfigurationInfo;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
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

public class TestOpenGLActivity extends BaseActivity {
    private GLSurfaceView glSurfaceView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //如果本设备支持OpenGl ES 2.0
        if (isSupportGL()) {
            Toast.makeText(this, "support", Toast.LENGTH_SHORT).show();
            //新建GLSurfaceView实例
            glSurfaceView = new GLSurfaceView(this);
            // 创建渲染器实例
            MyRenderer mRenderer = new MyRenderer();
            // 设置渲染器
            glSurfaceView.setRenderer(mRenderer);
            //设置渲染模式
            glSurfaceView.setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
            //显示SurfaceView
            setContentView(glSurfaceView);
        }
    }

    private boolean isSupportGL() {
        ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        ConfigurationInfo configurationInfo = activityManager.getDeviceConfigurationInfo();
        boolean isSupport = configurationInfo.reqGlEsVersion >= 0x2000;
        Log.i("openGLVersion", "isSupportGL: " + configurationInfo.getGlEsVersion()
                + "   " + isSupport);
        return isSupport;
    }
}
