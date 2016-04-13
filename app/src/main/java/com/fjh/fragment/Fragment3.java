package com.fjh.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fjh.activity.R;

/**
 * Created by fjh on 2016/3/29.
 */
public class Fragment3 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fg3 = inflater.inflate(R.layout.fragment_3, container, false);
        return fg3;
    }
}
