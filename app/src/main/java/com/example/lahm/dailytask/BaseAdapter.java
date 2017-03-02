package com.example.lahm.dailytask;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Project Name:DailyTask
 * Package Name:com.example.lahm.dailytask
 * Created by lahm on 2017/3/1 上午11:03 .
 * Copyright (c) 2017, www.footballzone.com All Rights Reserved.
 */

public abstract class BaseAdapter extends RecyclerView.Adapter {
    private int mItemlayoutResId;

    public BaseAdapter(int mItemlayoutResId) {
        this.mItemlayoutResId = mItemlayoutResId;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(v, position);
                }
            });
        }
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
}
