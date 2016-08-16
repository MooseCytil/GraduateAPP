package com.fjh.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.fjh.model.Hotel;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

/**
 * 酒店详情页的activity: hotel_layout.xml
 *  显示酒店详情，并可预订此酒店.
 * Author: fjh
 */
public class HotelInfoActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private AppCompatTextView textView_title; //toolbar标题
    private ViewPager mViewPager;
    private AppCompatTextView text_hotelname;
    private AppCompatTextView text_hoteladdress;
    private AppCompatTextView text_hotelphone;
    private AppCompatTextView text_hotelprice;
    private AppCompatTextView text_hotelinfo;
    private ImageView[] tips; //装圆点的ImageView数组
    private ImageView[] mImageViews; //图片数组
    private int[] src = new int[]{R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d, R.drawable.e};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hotel_layout);
        mViewPager = (ViewPager) findViewById(R.id.image_room);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        Intent intent = getIntent();

        String hotelName = intent.getStringExtra("hotelName");

        text_hotelname = (AppCompatTextView) findViewById(R.id.text_card_hotelname);
        text_hotelphone = (AppCompatTextView) findViewById(R.id.text_card_hotelphone);
        text_hoteladdress = (AppCompatTextView) findViewById(R.id.text_card_hoteladdress);
        text_hotelinfo = (AppCompatTextView) findViewById(R.id.text_card_hotelinfo);
        text_hotelprice = (AppCompatTextView) findViewById(R.id.text_card_hotelprice);

        toolbar.setNavigationIcon(R.drawable.left_24);
        toolbar.setTitle("上海浦东文华东方酒店"); //改正
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                startActivity(intent);
                onBackPressed();
            }
        });

        //设置酒店信息
        BmobQuery<Hotel> hotelBmobQuery = new BmobQuery<Hotel>();
        hotelBmobQuery.addWhereEqualTo("hotel_name", hotelName);
        hotelBmobQuery.findObjects(HotelInfoActivity.this, new FindListener<Hotel>() {
            @Override
            public void onSuccess(List<Hotel> list) {
                for (Hotel hotel : list)
                {
                    text_hotelname.setText(hotel.getHotel_name());
                    text_hoteladdress.setText(hotel.getHotel_address());
                    text_hotelphone.setText(hotel.getHotel_phone());
                    text_hotelinfo.setText(hotel.getHotel_config());
                    text_hotelprice.setText(hotel.getHotel_price());
                }
            }
            @Override
            public void onError(int i, String s) {
                Toast.makeText(getApplicationContext(), "数据错误", Toast.LENGTH_SHORT).show();
            }
        });
//        String bql = "select * from Hotel where hotel_name = ? ";
//        new BmobQuery<Hotel>().doSQLQuery(HotelInfoActivity.this, bql, new SQLQueryListener<Hotel>() { //执行SQL语句
//            @Override
//            public void done(BmobQueryResult<Hotel> bmobQueryResult, BmobException e) {
//                if (e == null) {
//                    List<Hotel> hotelList = bmobQueryResult.getResults();
//                    if (hotelList != null && hotelList.size() > 0) {
//                        for (Hotel hotel : hotelList) {
//                            text_hotelname.setText(hotel.getHotel_name());
//                            text_hoteladdress.setText(hotel.getHotel_address());
//                            text_hotelphone.setText(hotel.getHotel_phone());
//                            text_hotelinfo.setText(hotel.getHotel_config());
//                            text_hotelprice.setText(hotel.getHotel_price());
//                        }
//                    } else {
//                        Toast.makeText(getApplicationContext(), "无数据返回", Toast.LENGTH_SHORT).show();
//                    }
//                } else {
//                    Toast.makeText(getApplicationContext(), "数据错误", Toast.LENGTH_SHORT).show();
//                }
//            }
//        }, hotelName);

        initImage();
        initTips();

//      mViewPager.setOnPageChangeListener(); 此方法已过时，使用下面的addOnPageChangeListener().
        /**
         * 设置监听，主要是设置圆点的背景
         */
        mViewPager.addOnPageChangeListener(new OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                setTipBackground(position % src.length);
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //设置Adapter
        mViewPager.setAdapter(new HotelViewAdapter() );

    }

    /**
     * 初始化viewpager数据
     */
    private void initImage(){

        //图片资源，viewpager的图片展示
        mImageViews = new ImageView[src.length];
        //将数据循环输入链表中
        for (int i = 0; i < src.length; i ++)
        {
            ImageView image = new ImageView(getApplicationContext());
            mImageViews[i] = image;
            image.setImageResource(src[i]);

        }
    }

    //初始化圆点
    private void initTips(){
        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.viewGroup);

        //将点点加入到ViewGroup中
        tips = new ImageView[src.length];
        for (int i = 0; i < tips.length; i ++)
        {
            ImageView imageView = new ImageView(this); //实例化一个点
            imageView.setLayoutParams(new LayoutParams(2,2)); //第一个参数为宽，第二个参数为高
            tips[i] = imageView;
//          获取选中和未选中的图标
            if(i == 0){
                tips[i].setBackgroundResource(R.drawable.finalstate_10); //选中
            }else{
                tips[i].setBackgroundResource(R.drawable.activestate_10);  //未选中
            }
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT));
            layoutParams.leftMargin = 2;
            layoutParams.rightMargin = 2;
            viewGroup.addView(imageView, layoutParams);
        }
    }

    /**
     * 设置选中的tip的背景
     * @param selectItems
     */
    private void setTipBackground(int selectItems){
        for(int i = 0; i < tips.length; i ++){
            if(i == selectItems)
            {
                tips[i].setBackgroundResource(R.drawable.finalstate_10);
            }
            else
            {
                tips[i].setBackgroundResource(R.drawable.activestate_10);
            }
        }
    }

    //内部类
    public class HotelViewAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        /**
         * 载入图片进去，用当前的position 除以 图片数组长度取余数是关键
         */
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ((ViewPager)container).addView(mImageViews[position % mImageViews.length], 0);
            return mImageViews[position % mImageViews.length];
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager)container).removeView(mImageViews[position % mImageViews.length]);
        }
    }


}
