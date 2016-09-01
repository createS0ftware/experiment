package com.thesurix.example.gesturerecycler.adapter;

import com.thesurix.example.gesturerecycler.R;
import com.thesurix.gesturerecycler.GestureViewHolder;

import android.animation.ArgbEvaluator;
import android.animation.IntEvaluator;
import android.animation.ValueAnimator;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;


public class MonthViewHolder extends GestureViewHolder {

    private static final int SELECT_DURATION_IN_MS = 250;

    @Bind(R.id.month_text) TextView mMonthText;
    @Bind(R.id.month_image) ImageView mMonthPicture;
    @Bind(R.id.itemLayout) RelativeLayout mItemLayout;


    private int tempHeight;

    public MonthViewHolder(final View view) {
        super(view);
        ButterKnife.bind(this, view);
    }

    @Override
    public void onItemSelect() {
        final int textColorFrom = itemView.getContext().getResources().getColor(android.R.color.white);
        final int textColorTo = itemView.getContext().getResources().getColor(R.color.indigo_500);
        final ValueAnimator textAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), textColorFrom, textColorTo);
        textAnimation.setDuration(SELECT_DURATION_IN_MS);
        textAnimation.addUpdateListener(getTextAnimatorListener(mMonthText, textAnimation));
        textAnimation.start();

        final int backgroundColorFrom = itemView.getContext().getResources().getColor(R.color.indigo_500);
        final int backgroundColorTo = itemView.getContext().getResources().getColor(android.R.color.white);
        final ValueAnimator backgroundAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), backgroundColorFrom, backgroundColorTo);
        backgroundAnimation.setDuration(SELECT_DURATION_IN_MS);
        backgroundAnimation.addUpdateListener(getBackgroundAnimatorListener(mMonthText, backgroundAnimation));
        backgroundAnimation.start();

        tempHeight = itemView.getHeight();
        final int heightFrom    = tempHeight;
        final int heightTo      = mMonthText.getHeight();
        final ValueAnimator heightAnimation = ValueAnimator.ofObject(new IntEvaluator(), heightFrom, heightTo);
        heightAnimation.setDuration(SELECT_DURATION_IN_MS);
        heightAnimation.addUpdateListener(getLayoutAnimator(mMonthPicture, heightAnimation));
        heightAnimation.start();

    }

    @Override
    public void onItemClear() {
        final int textColorFrom = itemView.getContext().getResources().getColor(R.color.indigo_500);
        final int textColorTo = itemView.getContext().getResources().getColor(android.R.color.white);
        final ValueAnimator textAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), textColorFrom, textColorTo);
        textAnimation.setDuration(SELECT_DURATION_IN_MS);
        textAnimation.addUpdateListener(getTextAnimatorListener(mMonthText, textAnimation));
        textAnimation.start();

        final int backgroundColorFrom = itemView.getContext().getResources().getColor(android.R.color.white);
        final int backgroundColorTo = itemView.getContext().getResources().getColor(R.color.indigo_500);
        final ValueAnimator backgroundAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), backgroundColorFrom, backgroundColorTo);
        backgroundAnimation.setDuration(SELECT_DURATION_IN_MS);
        backgroundAnimation.addUpdateListener(getBackgroundAnimatorListener(mMonthText, backgroundAnimation));
        backgroundAnimation.start();

        final int heightFrom    = mMonthText.getHeight();
        final int heightTo      = tempHeight;
        final ValueAnimator heightAnimation = ValueAnimator.ofObject(new IntEvaluator(), heightFrom, heightTo);
        heightAnimation.setDuration(SELECT_DURATION_IN_MS);
        heightAnimation.addUpdateListener(getLayoutAnimator(mMonthPicture, heightAnimation));
        heightAnimation.start();
    }

    @Override
    public boolean canDrag() {
        return true;
    }

    @Override
    public boolean canSwipe() {
        return true;
    }

    private ValueAnimator.AnimatorUpdateListener getBackgroundAnimatorListener(final TextView view, final ValueAnimator animator) {
        return new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(final ValueAnimator valueAnimator) {
                view.setBackgroundColor((int) animator.getAnimatedValue());
            }
        };
    }

    private ValueAnimator.AnimatorUpdateListener getTextAnimatorListener(final TextView view, final ValueAnimator animator) {
        return new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(final ValueAnimator valueAnimator) {
                view.setTextColor((int) animator.getAnimatedValue());
            }
        };
    }

    private ValueAnimator.AnimatorUpdateListener getLayoutAnimator(final ImageView view, final ValueAnimator animator) {
        return new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(final ValueAnimator valueAnimator) {
                int val = (Integer) animator.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                layoutParams.height = val;
                view.setLayoutParams(layoutParams);
            }
        };
    }
}
