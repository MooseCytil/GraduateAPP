package com.fjh.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fjh.activity.R;
import com.fjh.adapter.RecyclerCardAdapter;

/**
 * Created by fjh on 2016/3/29.
 */
public class Fragment1 extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerCardAdapter mRecyclerCardAdapter;


    /*
    此fragment只用于显示文本
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //此方法将XML文件实例化为View类对象
        mRecyclerView = (RecyclerView) inflater.inflate(R.layout.fragment_1, container, false);
        return mRecyclerView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext()));
        mRecyclerView.setAdapter(new RecyclerCardAdapter(getActivity()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }


}
