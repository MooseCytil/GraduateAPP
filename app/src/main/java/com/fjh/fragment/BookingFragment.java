package com.fjh.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fjh.activity.R;

/**
 * “预订”页的fragment
 * Created by fjh on 2016/3/29.
 */
public class BookingFragment extends Fragment {

    private RecyclerView mRecyclerView2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRecyclerView2 = (RecyclerView)inflater.inflate(R.layout.booking_fragment, container, false);
        return mRecyclerView2;
    }
}
