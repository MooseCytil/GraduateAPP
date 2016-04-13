package com.fjh.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fjh.activity.R;

/**
 * Created by fjh on 2016/4/12.
 */

//未使用fragment
public class RecyclerCardAdapter extends RecyclerView.Adapter<RecyclerCardAdapter.MyViewHolder>{

    private Context mContext;

    public RecyclerCardAdapter (Context Context){
        this.mContext = Context;
    }


    /**
     * 负责为item创建视图
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    /**
     * 负责将数据绑定到视图上
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

    }



    public static class MyViewHolder extends RecyclerView.ViewHolder{
//        public ImageView imageView;
//        public AppCompatTextView textView;

        public final View mView;

        public MyViewHolder (View itemview){
            super(itemview);
            mView = itemview;
        }


    }
}
