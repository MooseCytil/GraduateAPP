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

import com.fjh.adapter.HotelListAdapter;
import com.fjh.model.Hotel;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobQueryResult;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SQLQueryListener;

public class HotelListActivity extends AppCompatActivity {

    private ListView lv_showCity;
    private HotelListAdapter adapter; //酒店列表
    private List<Hotel> list;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_list_activty);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.left_24);
        toolbar.setTitle("酒店列表");
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

    private void initlistener() {
        lv_showCity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String hotelName = list.get(position).getHotel_name();
                Intent intent = new Intent(HotelListActivity.this,HotelInfoActivity.class);
                intent.putExtra("hotelName",hotelName);
                startActivity(intent);
            }
        });
    }

    private void initData() {
        Intent intent = getIntent();
        String cityName = intent.getStringExtra("cityName");
        String bql="select * from Hotel where city_name = ?";
        new BmobQuery<Hotel>().doSQLQuery(HotelListActivity.this, bql, new SQLQueryListener<Hotel>() {
            @Override
            public void done(BmobQueryResult<Hotel> result, BmobException e) {
                // TODO Auto-generated method stub
                if (e == null) {
                    list = (List<Hotel>) result.getResults();
                    if (list != null && list.size() > 0) {
                        initAdapter();
                    } else {
                        Toast.makeText(HotelListActivity.this, "无返回数据~！", Toast.LENGTH_SHORT).show();
                        Log.i("smile", "查询成功，无数据返回");
                    }
                } else {
                    Toast.makeText(HotelListActivity.this, "获取数据失败~！", Toast.LENGTH_SHORT).show();
                    Log.i("smile", "错误码：" + e.getErrorCode() + "，错误描述：" + e.getMessage());
                }
            }
        }, cityName);

    }

    private void initAdapter() {
        adapter = new HotelListAdapter(HotelListActivity.this, list);
        lv_showCity.setAdapter(adapter);
    }

    private void initView() {
        lv_showCity = (ListView) findViewById(R.id.lv_showCity);
    }
}
