package com.fjh.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationClientOption.AMapLocationMode;
import com.amap.api.location.AMapLocationListener;
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
    private TextView text_location;
    private AMapLocationClient locationClient = null;
    private AMapLocationClientOption locationOption = new AMapLocationClientOption();

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

        text_location = (TextView) findViewById(R.id.text_location);
        text_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = text_location.getText().toString();
                Intent intent = new Intent(CityListActivity.this, HotelListActivity.class);
                intent.putExtra("cityName", name);
                startActivity(intent);
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

        //初始化定位
        initLocation();
        startLocation();

    }

    /**
     * No.1、初始化定位
     */
    private void initLocation(){
        //初始化client
        locationClient = new AMapLocationClient(this.getApplicationContext());
        //设置定位参数
        locationClient.setLocationOption(getDefaultOption());
        // 设置定位监听
        locationClient.setLocationListener(locationListener);
    }

    /**
     * No.2、配置定位参数
     */
    private AMapLocationClientOption getDefaultOption(){
        AMapLocationClientOption mOption = new AMapLocationClientOption();
        mOption.setLocationMode(AMapLocationMode.Hight_Accuracy);//可选，设置定位模式，可选的模式有高精度、仅设备、仅网络。默认为高精度模式
        mOption.setGpsFirst(true);//可选，设置是否gps优先，只在高精度模式下有效。默认关闭
        mOption.setHttpTimeOut(30000);//可选，设置网络请求超时时间。默认为30秒。在仅设备模式下无效
        mOption.setInterval(30000);//可选，设置定位间隔。
        mOption.setNeedAddress(true);//可选，设置是否返回逆地理地址信息。默认是ture
        mOption.setOnceLocation(false);//可选，设置是否单次定位。默认是false
        mOption.setOnceLocationLatest(true);//可选，设置是否等待wifi刷新，默认为false.如果设置为true,会自动变为单次定位，持续定位时不要使用
        return mOption;
    }

    /**
     * No.3、获取定位结果
     * 可以通过类implement方式实现AMapLocationListener接口，也可以通过创造接口匿名类对象的方法实现
     */
    AMapLocationListener locationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation amapLocation) {
            //解析AMapLocation对象
            if (amapLocation != null) {
                if (amapLocation.getErrorCode() == 0) {
                    //可在其中解析amapLocation获取相应内容。
                    StringBuffer stringBuffer = new StringBuffer(20);
                    stringBuffer.append(amapLocation.getCity());//城市信息
                    String city = stringBuffer.deleteCharAt(stringBuffer.length()-1).toString();
                    text_location.setText(city);
                }else {
                    //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                    Log.e("AmapError", "location Error, ErrCode:"
                            + amapLocation.getErrorCode() + ", errInfo:"
                            + amapLocation.getErrorInfo());
                }
            }
        }
    };

    /**
     * 开始定位
     */
    private void startLocation(){
        // 设置定位参数
        locationClient.setLocationOption(locationOption);
        // 启动定位
        locationClient.startLocation();
    }

    /**
     * 停止定位
     */
    private void stopLocation(){
        // 停止定位
        locationClient.stopLocation();
    }

    /**
     * 销毁定位
     */
    private void destroyLocation(){
        if (null != locationClient) {
            /**
             * 如果AMapLocationClient是在当前Activity实例化的，
             * 在Activity的onDestroy中一定要执行AMapLocationClient的onDestroy
             */
            locationClient.onDestroy();
            locationClient = null;
            locationOption = null;
        }
    }


}
