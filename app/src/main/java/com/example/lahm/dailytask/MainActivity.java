package com.example.lahm.dailytask;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.lahm.dailytask.File.FileActivity;
import com.example.lahm.dailytask.Reflection.ReflectionActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button thread_btn, file_btn, reflection_btn, replace_btn, search_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
    }

    private void initView() {
        thread_btn = (Button) findViewById(R.id.thread_btn);
        file_btn = (Button) findViewById(R.id.file_btn);
        reflection_btn = (Button) findViewById(R.id.reflection_btn);
        replace_btn = (Button) findViewById(R.id.replace_btn);
        search_btn = (Button) findViewById(R.id.search_btn);
    }

    private void initListener() {
        thread_btn.setOnClickListener(this);
        file_btn.setOnClickListener(this);
        reflection_btn.setOnClickListener(this);
        replace_btn.setOnClickListener(this);
        search_btn.setOnClickListener(this);
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
            default:
                break;
        }
    }
}
