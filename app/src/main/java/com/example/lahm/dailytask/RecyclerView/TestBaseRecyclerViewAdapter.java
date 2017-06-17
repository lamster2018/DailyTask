package com.example.lahm.dailytask.RecyclerView;

import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Project Name:DailyTask
 * Package Name:com.example.lahm.dailytask.View
 * Created by lahm on 2017/4/24 下午2:45 .
 * <p>
 * https://github.com/lamster2018
 */

public class TestBaseRecyclerViewAdapter extends BaseQuickAdapter {
    public TestBaseRecyclerViewAdapter(int layoutResId, List data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Object item) {

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }
}
