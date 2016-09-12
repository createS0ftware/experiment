package com.thesurix.example.gesturerecycler;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class DetailActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        setContentView(R.layout.activity_detail);
        ((ImageView)findViewById(R.id.month_image)).setImageDrawable(getResources().getDrawable(getIntent().getIntExtra("image",0)));

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}
