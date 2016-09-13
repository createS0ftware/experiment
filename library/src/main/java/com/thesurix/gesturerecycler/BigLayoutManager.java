package com.thesurix.gesturerecycler;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by hansonaboagye on 08/08/16.
 */
public class BigLayoutManager extends LinearLayoutManager {
    private static final int DEFAULT_EXTRA_LAYOUT_SPACE = 600;
    private int extraLayoutSpace = -1;
    private Context context;



    public BigLayoutManager(Context context, int extraLayoutSpace) {
        super(context);
        this.context = context;
        this.extraLayoutSpace = extraLayoutSpace;

    }

    public void setExtraLayoutSpace(int extraLayoutSpace)
    {
        this.extraLayoutSpace = extraLayoutSpace;
    }


    @Override
    protected int getExtraLayoutSpace(RecyclerView.State state) {
        if (extraLayoutSpace > 0) {
            return extraLayoutSpace;
        }
        return DEFAULT_EXTRA_LAYOUT_SPACE;
    }

    @Override
    public void scrollToPosition(int position) {
        super.scrollToPosition(position);
    }

    @Override
    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int position) {
        super.smoothScrollToPosition(recyclerView, state, position);
    }
}
