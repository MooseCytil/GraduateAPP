package com.fjh.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fjh.activity.R;
import com.fjh.model.Order;

import java.util.List;

/**
 * Created by fjh.
 */
public class OrderListAdapter extends BaseAdapter {

    private Context context ;
    private List<Order> orderList;
    private LayoutInflater inflater;

    public OrderListAdapter(Context context, List<Order> list) {
        this.context = context;
        this.orderList = list;
        inflater = LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        int i = 0;
        if (orderList != null)
        {
            i = orderList.size();
        }
        return i;
    }

    @Override
    public Object getItem(int position) {
        return orderList.get(position);
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
            convertView =inflater.inflate(R.layout.drawer_order_item,null);
            viewHolder.text_order_time = (TextView) convertView.findViewById(R.id.text_order_time);
            viewHolder.text_order_hotelname = (TextView) convertView.findViewById(R.id.text_order_hotelname);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.text_order_time.setText(orderList.get(position).getCreatedAt());

        viewHolder.text_order_hotelname.setText(orderList.get(position).getHotel_name());

        return convertView;
    }

    private class  ViewHolder{

        private TextView text_order_time, text_order_hotelname;
    }
}
