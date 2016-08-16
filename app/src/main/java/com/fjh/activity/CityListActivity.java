package com.fjh.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.fjh.adapter.CityListAdapter;
import com.fjh.model.City;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

/**
 * 城市列表
 * Created by fjh.
 */
public class CityListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CityListAdapter cityListAdapter;
    private Toolbar toolbar;
    private String TAG= "yu";
    private GridLayoutManager gridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.city_list);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.left_24);
        toolbar.setTitle("城市列表");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        BmobQuery<City> cityBmobQuery = new BmobQuery<City>();
        cityBmobQuery.findObjects(this, new FindListener<City>() {
            @Override
            public void onSuccess(final List<City> cityList) {
                recyclerView = (RecyclerView) findViewById(R.id.recycler_city);
                cityListAdapter = new CityListAdapter(CityListActivity.this,cityList);
                recyclerView.setAdapter(cityListAdapter);
                gridLayoutManager = new GridLayoutManager(CityListActivity.this,4);
                recyclerView.setLayoutManager(gridLayoutManager);
                cityListAdapter.setmOnItemClickListener(new CityListAdapter.CityItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        //item 监听
                        String cityName = cityList.get(position).getCity_name();
                        Intent intent = new Intent(CityListActivity.this,HotelListActivity.class);
                        intent.putExtra("cityName", cityName);
                        startActivity(intent);
                    }
                });
            }
            @Override
            public void onError(int i, String s) {
                Toast toast = Toast.makeText(getApplication(), "获取数据失败", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

    }


}
