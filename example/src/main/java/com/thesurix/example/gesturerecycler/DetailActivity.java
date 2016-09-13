package com.thesurix.example.gesturerecycler;

import android.animation.Animator;
import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v4.animation.ValueAnimatorCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class DetailActivity extends AppCompatActivity {

    RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(0,0);
        setContentView(R.layout.activity_detail);
        relativeLayout = (RelativeLayout) findViewById(R.id.mainLayout);
        ((ImageView)findViewById(R.id.month_image)).setImageDrawable(getResources().getDrawable(getIntent().getIntExtra("image",0)));

    }

    boolean readyToExit = false;

    @Override
    public void onBackPressed() {
        if (!readyToExit) {
            exitAnimation();
        }else{
            super.onBackPressed();
        }
    }

    private void exitAnimation()
    {
        // fade backgound
        ValueAnimator valueAnimator = ValueAnimator.ofObject(new ArgbEvaluator(),R.color.cardview_dark_background, R.color.cardview_light_background);
        valueAnimator.setInterpolator(new AccelerateInterpolator(1.5F));
        valueAnimator.setDuration(1400L);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                relativeLayout.setBackgroundColor((Integer) animation.getAnimatedValue());
            }
        });
        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                readyToExit = true;
                onBackPressed();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        valueAnimator.start();
    }
}
