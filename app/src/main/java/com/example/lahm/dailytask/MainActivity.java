package com.example.lahm.dailytask;

import android.app.KeyguardManager;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.lahm.dailytask.File.FileActivity;
import com.example.lahm.dailytask.OpenGL.TestOpenGLActivity;
import com.example.lahm.dailytask.RecyclerView.RecyclerViewActivity;
import com.example.lahm.dailytask.Reflection.ReflectionActivity;
import com.example.lahm.dailytask.Service.ServiceActivity;
import com.example.lahm.dailytask.Thread.ThreadActivity;

import java.util.Timer;
import java.util.TimerTask;

/**
 * https://github.com/lamster2018
 */
public class MainActivity extends BaseActivity {
    private KeyguardManager keyguardManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = (TextView) findViewById(R.id.channel);
        textView.setText(getApplicationMetaValue("CHANNEL"));
        initView();
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activation();
            }
        });
        keyguardManager = (KeyguardManager) getSystemService(KEYGUARD_SERVICE);
        timer.schedule(timerTask, 1000, 2000);
    }

    public Timer timer = new Timer();
    TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            //如果是锁屏状态下
            if (keyguardManager.inKeyguardRestrictedInputMode()) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("http://www.baidu.com"));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Log.i("ceshi", "run: ---");
                startActivity(intent);
            } else {
//                Intent intent = new Intent();
//                intent.setAction(Intent.ACTION_MAIN);
//                intent.addCategory(Intent.CATEGORY_HOME);
//                intent.addCategory(Intent.CATEGORY_DEFAULT);
//                intent.setData(Uri.parse("http://www.lizhiweike.com"));
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(intent);
            }
        }
    };

    // 激活设备超级管理员
    public void activation() {
        Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
        // 初始化要激活的组件
        ComponentName mDeviceAdminSample = new ComponentName(MainActivity.this, MyDeviceAdminReceiver.class);
        intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, mDeviceAdminSample);
        intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, "激活可以防止随意卸载应用");
        startActivity(intent);
    }

    /**
     * 简便写法
     * 写法搜索 泛型方法，该泛型通配上限是 继承了AppCompatActivity的泛型类型，not类
     * 传入的参数 是一个class类型的参数，该参数是继承了AppCompatActivity的targetActivity.class
     *
     * @param buttonRid
     * @param targetActivity
     * @param <T>
     */
    private <T extends AppCompatActivity> void setListener(int buttonRid,
                                                           final Class<T> targetActivity) {
        findViewById(buttonRid).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, targetActivity));
            }
        });
    }

    private void initView() {
        setListener(R.id.hook_btn, HookActivity.class);
        setListener(R.id.recycler_btn, RecyclerViewActivity.class);
        setListener(R.id.thread_btn, ThreadActivity.class);
        setListener(R.id.file_btn, FileActivity.class);
        setListener(R.id.reflection_btn, ReflectionActivity.class);
        setListener(R.id.replace_btn, ReplaceActivity.class);
        setListener(R.id.search_btn, SearchActivity.class);
        setListener(R.id.screen_btn, ScreenActivity.class);
        setListener(R.id.lifeCircle_btn, LifeCircleActivity.class);
        setListener(R.id.memoryLeak_btn, MemoryLeakActivity.class);
        setListener(R.id.web_btn, WebActivity.class);
        setListener(R.id.ndk_btn, NDKActivity.class);
        setListener(R.id.service_btn, ServiceActivity.class);
        setListener(R.id.test_openGl_btn, TestOpenGLActivity.class);
    }

    private String getApplicationMetaValue(String name) {
        String value = "";
        try {
            ApplicationInfo appInfo = getPackageManager()
                    .getApplicationInfo(getPackageName(),
                            PackageManager.GET_META_DATA);
            value = appInfo.metaData.getString(name);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return value;
    }

}
