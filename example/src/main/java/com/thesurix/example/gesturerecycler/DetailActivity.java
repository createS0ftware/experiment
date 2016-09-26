package com.thesurix.example.gesturerecycler;

import android.animation.Animator;
import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v4.animation.ValueAnimatorCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    @Bind(R.id.month_image2)
    ImageView detailImage;
    @Bind(R.id.view)
    CardView cardView;

    @Bind(R.id.mainLayout)
    RelativeLayout relativeLayout;

    @Bind(R.id.month_text2)
    TextView monthText;

    @Bind(R.id.scrollView)
    ScrollView scrollView;

    @Bind(R.id.frame)
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(0,0);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        monthText.setText("MAY");
        detailImage.setImageDrawable(getResources().getDrawable(getIntent().getIntExtra("image",0)));

        startAnimation();
    }

    private void startAnimation()
    {

        final Animation bottomUp = AnimationUtils.loadAnimation(this,
                R.anim.slide_up);

        AlphaAnimation fadeInAnimation = new AlphaAnimation(0f, 1f);
        fadeInAnimation.setDuration(400L);
        fadeInAnimation.setFillAfter(true);
        fadeInAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                relativeLayout.setVisibility(View.VISIBLE);

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                scrollView.startAnimation(bottomUp);
                scrollView.setVisibility(View.VISIBLE);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        relativeLayout.startAnimation(fadeInAnimation);


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

        AlphaAnimation alphaAnimation = new AlphaAnimation(1f,0f);
        alphaAnimation.setDuration(500);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                readyToExit = true;
                onBackPressed();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        relativeLayout.startAnimation(alphaAnimation);
    }
}
