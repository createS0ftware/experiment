package com.thesurix.example.gesturerecycler.fragment;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.thesurix.example.gesturerecycler.DetailActivity;
import com.thesurix.example.gesturerecycler.R;
import com.thesurix.example.gesturerecycler.adapter.MonthsAdapter;
import com.thesurix.example.gesturerecycler.model.Month;
import com.thesurix.gesturerecycler.BigLayoutManager;
import com.thesurix.gesturerecycler.DefaultItemClickListener;
import com.thesurix.gesturerecycler.GestureAdapter;
import com.thesurix.gesturerecycler.GestureManager;
import com.thesurix.gesturerecycler.RecyclerItemTouchListener;

public class LinearRecyclerFragment extends BaseFragment {


    ImageView ivProfile;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_recycler, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);


        return rootView;
    }

    Point lastPos;
    MonthsAdapter adapter;
    View lastView;

    @Override
    public void onViewCreated(final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final BigLayoutManager manager = new BigLayoutManager(getContext(), 680);
        adapter = new MonthsAdapter(getContext(), R.layout.linear_item);
        adapter.setData(getMonths());
        lastPos = new Point();
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        //  manager.setExtraLayoutSpace(DeviceUtils.getScreenHeight(getContext()));
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.addOnItemTouchListener(new RecyclerItemTouchListener(getActivity(), new DefaultItemClickListener() {
            @Override
            public boolean onItemClick(final View view, final int position) {
                lastPos = new Point(Float.valueOf(view.getX()).intValue(), Float.valueOf(view.getY()).intValue());
                lastView = view;
                Animation movingAnim = new TranslateAnimation(0, 0, 0, -view.getTop());
                movingAnim.setDuration(800);
                movingAnim.setFillAfter(true);
                movingAnim.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        Intent dataInfo = new Intent(getActivity(), DetailActivity.class);
                        dataInfo.putExtra("image", adapter.getData().get(position).getDrawableId());
                        startActivityForResult(dataInfo, 99);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                view.startAnimation(movingAnim);


                return true;
            }

            @Override
            public void onItemLongPress(final View view, final int position) {

            }

            @Override
            public boolean onDoubleTap(final View view, final int position) {
                Snackbar.make(view, "Double tap event on the " + position + " position", Snackbar.LENGTH_SHORT).show();
                return true;
            }
        }));

        mGestureManager = new GestureManager.Builder(mRecyclerView)
                .setSwipeEnabled(true)
                .setLongPressDragEnabled(true)
                .setGestureFlags(ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT, ItemTouchHelper.UP | ItemTouchHelper.DOWN)
                .build();

        adapter.setDataChangeListener(new GestureAdapter.OnDataChangeListener<Month>() {
            @Override
            public void onItemRemoved(final Month item, final int position) {
                Snackbar.make(view, "Month removed from position " + position, Snackbar.LENGTH_SHORT).show();
            }

            @Override
            public void onItemReorder(final Month item, final int fromPos, final int toPos) {
                Snackbar.make(view, "Month moved from position " + fromPos + " to " + toPos, Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        lastView.setTop(0);
        Animation movingAnim = new TranslateAnimation(0, 0, 0, lastPos.y);
        movingAnim.setDuration(800);
        movingAnim.setFillAfter(true);
        movingAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mRecyclerView.invalidate();
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        lastView.startAnimation(movingAnim);


    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        if (item.getItemId() == R.id.recycler_drag_menu) {
            mGestureManager.setManualDragEnabled(!mGestureManager.isManualDragEnabled());
        }
        return super.onOptionsItemSelected(item);
    }
}
