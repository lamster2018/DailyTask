package com.example.lahm.dailytask;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.lahm.dailytask.File.FileActivity;
import com.example.lahm.dailytask.Reflection.ReflectionActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button thread_btn, file_btn, reflection_btn, replace_btn, search_btn, screen_btn;
    private Button lifeCircle_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = (TextView) findViewById(R.id.channel);
        textView.setText(getApplicationMetaValue("CHANNEL"));
        initView();
        initListener();
    }

    private void initView() {
        thread_btn = (Button) findViewById(R.id.thread_btn);
        file_btn = (Button) findViewById(R.id.file_btn);
        reflection_btn = (Button) findViewById(R.id.reflection_btn);
        replace_btn = (Button) findViewById(R.id.replace_btn);
        search_btn = (Button) findViewById(R.id.search_btn);
        screen_btn = (Button) findViewById(R.id.screen_btn);
        lifeCircle_btn = (Button) findViewById(R.id.lifeCircle_btn);
    }

    private void initListener() {
        thread_btn.setOnClickListener(this);
        file_btn.setOnClickListener(this);
        reflection_btn.setOnClickListener(this);
        replace_btn.setOnClickListener(this);
        search_btn.setOnClickListener(this);
        screen_btn.setOnClickListener(this);
        lifeCircle_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.thread_btn:
                startActivity(new Intent(this, ThreadActivity.class));
                break;
            case R.id.file_btn:
                startActivity(new Intent(this, FileActivity.class));
                break;
            case R.id.reflection_btn:
                startActivity(new Intent(this, ReflectionActivity.class));
                break;
            case R.id.replace_btn:
                startActivity(new Intent(this, ReplaceActivity.class));
                break;
            case R.id.search_btn:
                startActivity(new Intent(this, SearchActivity.class));
                break;
            case R.id.screen_btn:
                startActivity(new Intent(this, ScreenActivity.class));
                break;
            case R.id.lifeCircle_btn:
                startActivity(new Intent(this, LifeCircleActivity.class));
                break;
            default:
                break;
        }
    }


    private String getApplicationMetaValue(String name) {
        String value = "";
        try {
            ApplicationInfo appInfo = getPackageManager()
                    .getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA);
            value = appInfo.metaData.getString(name);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return value;
    }

}
