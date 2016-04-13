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
    private LinearLayoutManager mLinearLayoutManager;


    /*
    此fragment只用于显示文本
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fg1 = inflater.inflate(R.layout.fragment_1, container, false); //此方法将XML文件实例化为View类对象
        mRecyclerView = (RecyclerView)fg1.findViewById(R.id.recycler);
        return fg1;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        String[] dataset = new String[100];
        for (int i = 0; i < dataset.length; i++) {
            dataset[i] = "item" + i;
        }
        mRecyclerCardAdapter = new RecyclerCardAdapter(dataset);
        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setAdapter(mRecyclerCardAdapter);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }


}
