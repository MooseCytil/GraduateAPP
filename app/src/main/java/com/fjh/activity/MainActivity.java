package com.fjh.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.Calendar;

/**
 * 显示首页信息
 * Created by fjh
 */

public class MainActivity extends AppCompatActivity {

//    private TabLayout tabLayout; //定义tablayout
    private ViewPager mViewPager; //定义viewpager
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private AppCompatEditText editTextCheckIn;
    private AppCompatEditText editTextCheckOut;
    private SearchView search;
    private Calendar calendar; //通过Calendar获取系统时间
    private ImageView image;
    private ImageView[] tips; //装圆点的ImageView数组
    private ImageView[] mImageViews; //图片数组
    private int[] src = new int[]{R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d, R.drawable.e};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main); //给当前activity引入的布局文件

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false); //隐藏title
        final ActionBar actionBar = getSupportActionBar();
        calendar = Calendar.getInstance(); // 获取日历对象

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        mNavigationView = (NavigationView) findViewById(R.id.navigationview);
        editTextCheckIn = (AppCompatEditText) findViewById(R.id.edit_checkin);
        editTextCheckOut = (AppCompatEditText) findViewById(R.id.edit_checkout);
        search = (SearchView)findViewById(R.id.main_search);
//        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView_home);

        //设置导航栏按钮
        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.menu_28);
            actionBar.setDisplayHomeAsUpEnabled(true); // 给左上角图标的左边加上一个返回的图标
        }

        search.setQueryHint("请输入您的目的地");

        /*
        setNavigationItemSelectedListener设置当导航项被点击时的回调。
        OnNavigationItemSelectedListener提供给我们被选中的MenuItem，
        与Activity的onOptionsItemSelected类似。
        */
        mNavigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {

                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        switch (menuItem.getItemId())
                        {
                            case R.id.nav_reserved:
                                Intent intent1 = new Intent(MainActivity.this, BookingActivity.class);
                                startActivity(intent1);
//                                toolbar.setTitle("即将开始的旅程"); //设置toolbar的title
                                break;
                            case R.id.nav_history:
                                Intent intent2 = new Intent(MainActivity.this, DrawerHistoryActivity.class);
                                startActivity(intent2);
//                                toolbar.setTitle("历史订单");
                                break;
                            case R.id.nav_setting:
                                Intent intent3 = new Intent(MainActivity.this, DrawerSettingActivity.class);
                                startActivity(intent3);
//                                toolbar.setTitle("系统设置");
                                break;
                            case R.id.nav_more:
                                Intent intent4 = new Intent(MainActivity.this, DrawerMoreActivity.class);
                                startActivity(intent4);
//                                toolbar.setTitle("关于我们");
                                break;
                        }
                        menuItem.setChecked(true);//点击了把它设为选中状态
                        mDrawerLayout.closeDrawers();//关闭抽屉
                        return true;
                    }
                }
        );

        image = (ImageView) findViewById(R.id.drawer_image_login);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        //设置viewpager的main
        mViewPager = (ViewPager) findViewById(R.id.viewpager_main);
        initImage();
        initTips();

        /**
         * 设置监听，主要是设置圆点的背景
         */
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

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

        mViewPager.setAdapter(new MainViewAdapter());

//        mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext()));
//        mRecyclerView.setAdapter(new RecyclerCardAdapter(getApplicationContext()));
//        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        editTextCheckIn = (AppCompatEditText) findViewById(R.id.edit_checkin);
        editTextCheckOut = (AppCompatEditText) findViewById(R.id.edit_checkout);
        editTextCheckIn.setInputType(InputType.TYPE_NULL);
        editTextCheckOut.setInputType(InputType.TYPE_NULL);

        editTextCheckIn.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                            editTextCheckIn.setText(year + "年" + monthOfYear + "月" + dayOfMonth + "日");
                        }
                    }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
                }
            }
        });
        editTextCheckIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        editTextCheckIn.setText(year + "年" + monthOfYear + "月" + dayOfMonth + "日");
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        editTextCheckOut.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                            editTextCheckOut.setText(year + "年" + monthOfYear + "月" + dayOfMonth + "日");
                        }
                    }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
                }
            }
        });
        editTextCheckOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        editTextCheckOut.setText(year + "年" + monthOfYear + "月" + dayOfMonth + "日");
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


    }

    /**
     * 实现导航栏按钮可以弹出侧滑菜单
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
        {
            mDrawerLayout.openDrawer(GravityCompat.START);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 初始化viewpager数据
     */
    private void initImage(){

        //图片资源，viewpager的图片展示
        mImageViews = new ImageView[src.length];
        //将数据循环输入数组中
        for (int i = 0; i < src.length; i ++)
        {
            ImageView image = new ImageView(getApplicationContext());
            mImageViews[i] = image;
            image.setBackgroundResource(src[i]);

        }
    }

    //初始化圆点
    private void initTips(){
        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.linear_main);

        //将圆点加入到ViewGroup中,有多少张图就有多少个圆点
        tips = new ImageView[src.length];
        for (int i = 0; i < tips.length; i ++)
        {
            ImageView imageView = new ImageView(this); //实例化一个点
            imageView.setLayoutParams(new ViewGroup.LayoutParams(2,2)); //第一个参数为宽，第二个参数为高
            tips[i] = imageView;
//          获取选中和未选中的图标
            if(i == 0){
                tips[i].setBackgroundResource(R.drawable.finalstate_10); //选中
            }else{
                tips[i].setBackgroundResource(R.drawable.activestate_10);  //未选中
            }
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
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

    //内部类适配器
    public class MainViewAdapter extends PagerAdapter {

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
