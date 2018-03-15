package com.example.lahm.dailytask.Hook;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.lahm.dailytask.BaseActivity;
import com.example.lahm.dailytask.R;

/**
 * Project Name:DailyTask
 * Package Name:com.example.lahm.dailytask.Hook
 * Created by lahm on 2018/3/14 下午5:52 .
 * <p>
 * 没有进行注册的activity
 */

public class TargetActivity extends BaseActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_target);
    }
}
