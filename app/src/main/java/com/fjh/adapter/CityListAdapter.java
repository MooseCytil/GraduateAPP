package com.fjh.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fjh.activity.R;
import com.fjh.model.City;

import java.util.List;

/**
 * 城市列表适配器
 * Created by fjh.
 */
public class CityListAdapter extends RecyclerView.Adapter<CityListAdapter.CityViewHolder> {

    public List<City> mCityList;
//    private CityItemClick mCityItemClick;
    private Context context;

    private LayoutInflater inflater;

    public CityListAdapter(Context context,List<City> cities){
        this.context =context;
        this.mCityList = cities;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public CityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.city_list_item, parent, false);//查找layout文件
        return new CityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CityViewHolder holder, final int position) {
        City city = mCityList.get(position);
        holder.textView_city.setText(city.getCity_name());
        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(holder.itemView,position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        int size = 0;
        if (mCityList != null) {
            size = mCityList.size();
        }

        return size;
    }

    public static class CityViewHolder extends RecyclerView.ViewHolder{

        TextView textView_city;

        public CityViewHolder(View itemView) {
            super(itemView);
            textView_city = (TextView) itemView.findViewById(R.id.text_city);
            //为item设置点击事件

        }

    }

    private  CityItemClickListener mOnItemClickListener;

    public void setmOnItemClickListener(CityItemClickListener listener){

        this.mOnItemClickListener = listener;

    };

    //自定义的listener
    public  interface CityItemClickListener{
        //单击整个item跳转到酒店信息界面，需要传递酒店名字
        void onItemClick(View view, int position);
    }


}
