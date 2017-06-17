package com.example.lahm.dailytask.RecyclerView;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.example.lahm.dailytask.BaseActivity;
import com.example.lahm.dailytask.R;

/**
 * Project Name:DailyTask
 * Package Name:com.example.lahm.dailytask.RecyclerView
 * Created by lahm on 2017/5/24 下午10:14 .
 * <p>
 * Copyright (c) 2016—2017 https://www.lizhiweike.com all rights reserved.
 */

public class RecyclerViewActivity extends BaseActivity {
    private RecyclerView recyclerView;
    private TestBaseRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

    }
}
