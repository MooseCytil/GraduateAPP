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
import com.fjh.util.adapter.RecyclerCardAdapter;

/**
 * “首页”的fragment：
 *  用于获取cardview的数据
 * Author: fjh on 2016/3/29.
 */
public class HomeFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerCardAdapter mRecyclerCardAdapter; //适配器
    private LinearLayoutManager mLinearLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //此方法将XML文件实例化为View类对象
        mRecyclerView = (RecyclerView) inflater.inflate(R.layout.home_fragment, container, false);
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
