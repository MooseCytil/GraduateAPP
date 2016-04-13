package com.fjh.adapter;

import android.support.v7.widget.AppCompatTextView;
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

    public String[] mdata ;

    public RecyclerCardAdapter (String[] data){
        this.mdata = data;
    }


    /**
     * 负责为item创建视图
     * @param viewgroup
     * @param i
     * @return
     */
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewgroup, int i) {
        View v = LayoutInflater.from(viewgroup.getContext()).inflate(R.layout.item_cardview, viewgroup, false);
        return new MyViewHolder(v);
    }

    @Override
    public int getItemCount() {
        return mdata.length;
    }

    /**
     * 负责将数据绑定到视图上
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.textView.setText(mdata[position]);
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{
//        public ImageView imageView;
        public AppCompatTextView textView;

        public MyViewHolder (View itemview){
            super(itemview);
            textView = (AppCompatTextView) itemview.findViewById(R.id.cardtext);
        }
    }
}
