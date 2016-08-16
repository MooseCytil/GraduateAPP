package com.fjh.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fjh.activity.R;
import com.fjh.model.Hotel;

import java.util.List;

/**
 * hotel列表
 * Created by fjh.
 */
public class HotelListAdapter extends BaseAdapter {


    private Context context ;
    private  List<Hotel> list;
    private LayoutInflater inflater;
    public HotelListAdapter(Context context, List<Hotel> list) {

        this.context =context;
        this.list = list;
        inflater = LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        int i = 0;
        if (list != null)
        {
            i = list.size();
        }
        return i;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder ;
        if (convertView == null) {
            viewHolder =new ViewHolder();
            convertView =inflater.inflate(R.layout.item_city_show,null);

            viewHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            viewHolder.tv_address = (TextView) convertView.findViewById(R.id.tv_address);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tv_name.setText(list.get(position).getHotel_name());

        viewHolder.tv_address.setText(list.get(position).getHotel_address());

        return convertView;
    }


    private class  ViewHolder{


       private TextView tv_name,tv_address;


    }
}
