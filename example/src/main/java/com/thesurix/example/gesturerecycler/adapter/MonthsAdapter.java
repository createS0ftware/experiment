package com.thesurix.example.gesturerecycler.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.thesurix.example.gesturerecycler.model.Month;
import com.thesurix.gesturerecycler.GestureAdapter;
import com.thesurix.gesturerecycler.GestureViewHolder;

public class MonthsAdapter extends GestureAdapter<Month, GestureViewHolder> {

    private final Context mCtx;
    private final int mItemResId;

    public MonthsAdapter(final Context ctx, @LayoutRes final int itemResId) {
        mCtx = ctx;
        mItemResId = itemResId;
    }


    @Override
    public GestureViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext()).inflate(mItemResId, parent, false);
        return new MonthViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(final GestureViewHolder holder, final int position) {
        super.onBindViewHolder(holder, position);

        final MonthViewHolder monthViewHolder = (MonthViewHolder) holder;
        final Month month = getData().get(position);
        monthViewHolder.mMonthText.setText(month.getName());
        Glide.with(mCtx).load(month.getDrawableId()).diskCacheStrategy(DiskCacheStrategy.SOURCE).centerCrop().into(monthViewHolder.mMonthPicture);


        //holder.itemView.setLayoutParams(params);

    }

}
