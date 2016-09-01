package com.thesurix.example.gesturerecycler.fragment;

import com.thesurix.example.gesturerecycler.R;
import com.thesurix.example.gesturerecycler.model.Month;
import com.thesurix.gesturerecycler.GestureManager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class BaseFragment extends Fragment {

    protected RecyclerView mRecyclerView;
    protected GestureManager mGestureManager;

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        mRecyclerView = new RecyclerView(getActivity());
        return mRecyclerView;
    }

    @Override
    public void onCreateOptionsMenu(final Menu menu, final MenuInflater inflater) {
        inflater.inflate(R.menu.recycler_menu, menu);
    }

    protected List<Month> getMonths() {
        final List<Month> monthList = new ArrayList<>();

        monthList.add(new Month("JAN", R.drawable.january));
        monthList.add(new Month("FEB", R.drawable.february));
        monthList.add(new Month("MAR", R.drawable.march));

        monthList.add(new Month("APR", R.drawable.april));
        monthList.add(new Month("MAY", R.drawable.may));
        monthList.add(new Month("JUN", R.drawable.june));

        monthList.add(new Month("JUL", R.drawable.july));
        monthList.add(new Month("AUG", R.drawable.august));
        monthList.add(new Month("SEP", R.drawable.september));

        monthList.add(new Month("OCT", R.drawable.october));
        monthList.add(new Month("NOV", R.drawable.november));
        monthList.add(new Month("DEC", R.drawable.december));

        return monthList;
    }

}
