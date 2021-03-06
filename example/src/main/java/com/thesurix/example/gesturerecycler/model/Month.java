package com.thesurix.example.gesturerecycler.model;


import android.support.annotation.DrawableRes;

public class Month  {

    private String mName;
    private int mDrawableId;

    public Month(final String name, @DrawableRes final int drawableId) {
        mName = name;
        mDrawableId = drawableId;
    }

    public String getName() {
        return mName;
    }

    public int getDrawableId() {
        return mDrawableId;
    }
}
