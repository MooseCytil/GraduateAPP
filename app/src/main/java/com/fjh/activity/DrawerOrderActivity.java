package com.fjh.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.fjh.AppManager;
import com.fjh.adapter.OrderListAdapter;
import com.fjh.model.Order;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobQueryResult;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SQLQueryListener;

/**
 * 订单列表
 * Created by fjh.
 */
public class DrawerOrderActivity extends AppCompatActivity {

    private ListView lv_showOrder;
    private OrderListAdapter orderAdapter; //订单列表
    private List<Order> orderList;
    private Toolbar toolbar;
    private AppManager appManager; //全局变量

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_order);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.left_24);
        toolbar.setTitle("订单信息");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        initView();
        initData();
        initlistener();
    }

    //查看酒店信息按钮
    private void initlistener() {
        lv_showOrder.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String hotelName = orderList.get(position).getHotel_name();
                Intent intent = new Intent(DrawerOrderActivity.this, HotelInfoActivity.class);
                intent.putExtra("hotelName", hotelName);
                startActivity(intent);
            }
        });
    }

    private void initData() {
        String userName = ((AppManager)getApplication()).getUsername();;
        String bql="select * from Order where user_name = ?";
        new BmobQuery<Order>().doSQLQuery(DrawerOrderActivity.this, bql, new SQLQueryListener<Order>() {
            @Override
            public void done(BmobQueryResult<Order> result, BmobException e) {
                // TODO Auto-generated method stub
                if (e == null) {
                    orderList = (List<Order>) result.getResults();
                    if (orderList != null && orderList.size() > 0) {
                        initAdapter();
                    } else {
                        Toast.makeText(DrawerOrderActivity.this, "无返回数据~！", Toast.LENGTH_SHORT).show();
                        Log.i("smile", "查询成功，无数据返回");
                    }
                } else {
                    Toast.makeText(DrawerOrderActivity.this, "获取数据失败~！", Toast.LENGTH_SHORT).show();
                    Log.i("smile", "错误码：" + e.getErrorCode() + "，错误描述：" + e.getMessage());
                }
            }
        }, userName);

    }

    private void initView() {
        lv_showOrder = (ListView) findViewById(R.id.listview_order);
    }

    //设置适配器
    private void initAdapter() {
        orderAdapter = new OrderListAdapter(DrawerOrderActivity.this, orderList);
        lv_showOrder.setAdapter(orderAdapter);
    }
}
