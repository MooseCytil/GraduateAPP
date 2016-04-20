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

/**
 * 酒店详情页的activity: hotel_layout.xml
 *  显示酒店详情，并可预订此酒店.
 * Author: fjh
 */
public class BookingActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private AppCompatTextView textView_title;
    private ViewPager mViewPager;
    private ImageView[] tips; //装圆点的ImageView数组
    private ImageView[] mImageViews; //图片数组
    private int[] src = new int[]{R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d, R.drawable.e};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hotel_layout);
        mViewPager = (ViewPager) findViewById(R.id.image_room);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        textView_title = (AppCompatTextView) findViewById(R.id.text_title);

        toolbar.setNavigationIcon(R.drawable.left_24);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                startActivity(intent);
                onBackPressed();
            }
        });

        textView_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BookingActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

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
